package cl.alke.wallet.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import cl.alke.wallet.exception.SaldoInsuficienteException;
import cl.alke.wallet.model.Account;
import cl.alke.wallet.model.Currency;

public class WalletServiceTest {

    private Account acc;
    private IWalletService wallet;

    @Before
    public void setUp() {
        acc = new Account("ACC-TEST", "Tester");
        wallet = new WalletService();
    }

    @Test
    public void deposit_increases_balance() {
        wallet.deposit(acc, new BigDecimal("1000"), Currency.CLP);
        assertEquals(new BigDecimal("1000"), wallet.getBalance(acc, Currency.CLP));
    }

    @Test
    public void withdraw_decreases_balance() {
        wallet.deposit(acc, new BigDecimal("1000"), Currency.CLP);
        wallet.withdraw(acc, new BigDecimal("400"), Currency.CLP);
        assertEquals(new BigDecimal("600"), wallet.getBalance(acc, Currency.CLP));
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void withdraw_more_than_balance_throws() {
        wallet.deposit(acc, new BigDecimal("100"), Currency.CLP);
        wallet.withdraw(acc, new BigDecimal("200"), Currency.CLP);
    }

    @Test
    public void convert_moves_funds_between_currencies() {
        wallet.deposit(acc, new BigDecimal("20000"), Currency.CLP);
        wallet.convert(acc, new BigDecimal("20000"), Currency.CLP, Currency.USD);

        assertEquals(new BigDecimal("0"), wallet.getBalance(acc, Currency.CLP));
        assertTrue(wallet.getBalance(acc, Currency.USD).compareTo(BigDecimal.ZERO) > 0);
    }
}
