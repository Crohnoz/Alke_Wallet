package cl.alke.wallet.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import cl.alke.wallet.exception.SaldoInsuficienteException;
import cl.alke.wallet.model.Account;
import cl.alke.wallet.model.Currency;

public class WalletService implements IWalletService {

    @Override
    public void deposit(Account account, BigDecimal amount, Currency currency) {
        validateAmount(amount);

        BigDecimal current = account.getBalance(currency);
        account.setBalance(currency, current.add(amount));
    }

    @Override
    public void withdraw(Account account, BigDecimal amount, Currency currency) {
        validateAmount(amount);

        BigDecimal current = account.getBalance(currency);
        if (current.compareTo(amount) < 0) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente en " + currency + ". Saldo: " + current + ", retiro: " + amount
            );
        }

        account.setBalance(currency, current.subtract(amount));
    }

    @Override
    public BigDecimal getBalance(Account account, Currency currency) {
        return account.getBalance(currency);
    }

    @Override
    public void convert(Account account, BigDecimal amount, Currency from, Currency to) {
        validateAmount(amount);

        // 1) Retira en moneda origen
        withdraw(account, amount, from);

        // 2) Convierte con una tasa fija (demo)
        BigDecimal rate = getFixedRate(from, to);
        BigDecimal converted = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);

        // 3) Deposita en moneda destino
        deposit(account, converted, to);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
    }

    /**
     * Tasa fija para demo (puedes cambiarla después).
     * CLP <-> USD, CLP <-> EUR, USD <-> EUR
     */
    private BigDecimal getFixedRate(Currency from, Currency to) {
        if (from == to) return BigDecimal.ONE;

        // Ejemplo: 1 USD = 900 CLP, 1 EUR = 1000 CLP
        if (from == Currency.USD && to == Currency.CLP) return new BigDecimal("900");
        if (from == Currency.CLP && to == Currency.USD) return new BigDecimal("0.00111111");

        if (from == Currency.EUR && to == Currency.CLP) return new BigDecimal("1000");
        if (from == Currency.CLP && to == Currency.EUR) return new BigDecimal("0.001");

        if (from == Currency.USD && to == Currency.EUR) return new BigDecimal("0.90");
        if (from == Currency.EUR && to == Currency.USD) return new BigDecimal("1.11");

        throw new IllegalArgumentException("No hay tasa configurada para " + from + " -> " + to);
    }
}
