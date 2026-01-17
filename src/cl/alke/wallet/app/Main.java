package cl.alke.wallet.app;

import java.math.BigDecimal;

import cl.alke.wallet.model.Account;
import cl.alke.wallet.model.Currency;
import cl.alke.wallet.service.IWalletService;
import cl.alke.wallet.service.WalletService;

public class Main {

    public static void main(String[] args) {

        Account acc = new Account("ACC-001", "Enrique");
        IWalletService wallet = new WalletService();

        wallet.deposit(acc, new BigDecimal("50000"), Currency.CLP);
        System.out.println("Saldo CLP: " + wallet.getBalance(acc, Currency.CLP));

        wallet.withdraw(acc, new BigDecimal("10000"), Currency.CLP);
        System.out.println("Saldo CLP tras retiro: " + wallet.getBalance(acc, Currency.CLP));

        wallet.convert(acc, new BigDecimal("20000"), Currency.CLP, Currency.USD);

        System.out.println("Saldo CLP final: " + wallet.getBalance(acc, Currency.CLP));
        System.out.println("Saldo USD final: " + wallet.getBalance(acc, Currency.USD));
    }
}
