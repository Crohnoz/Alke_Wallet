package cl.alke.wallet.service;

import java.math.BigDecimal;

import cl.alke.wallet.model.Account;
import cl.alke.wallet.model.Currency;

public interface IWalletService {

    void deposit(Account account, BigDecimal amount, Currency currency);

    void withdraw(Account account, BigDecimal amount, Currency currency);

    BigDecimal getBalance(Account account, Currency currency);

    void convert(Account account, BigDecimal amount, Currency from, Currency to);
}
