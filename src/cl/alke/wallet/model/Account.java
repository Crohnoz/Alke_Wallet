package cl.alke.wallet.model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class Account {

    private final String id;
    private final String owner;
    private final Map<Currency, BigDecimal> balances;

    public Account(String id, String owner) {
        this.id = id;
        this.owner = owner;
        this.balances = new EnumMap<>(Currency.class);

        // Inicializa todas las monedas en 0
        for (Currency c : Currency.values()) {
            balances.put(c, BigDecimal.ZERO);
        }
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getBalance(Currency currency) {
        return balances.get(currency);
    }

    public void setBalance(Currency currency, BigDecimal newBalance) {
        balances.put(currency, newBalance);
    }
}
