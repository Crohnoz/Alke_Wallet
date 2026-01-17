package cl.alke.wallet.app;

import java.math.BigDecimal;
import java.util.Scanner;

import cl.alke.wallet.exception.SaldoInsuficienteException;
import cl.alke.wallet.model.Account;
import cl.alke.wallet.model.Currency;
import cl.alke.wallet.service.IWalletService;
import cl.alke.wallet.service.WalletService;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== ALKE WALLET (Console) ===");

        // Cuenta demo (puedes pedirlo por consola si quieres)
        Account acc = new Account("ACC-001", "Enrique");
        IWalletService wallet = new WalletService();

        boolean running = true;
        while (running) {
            printMenu();
            int option = readInt("Opción: ");

            switch (option) {
                case 1:
                    handleDeposit(wallet, acc);
                    break;
                case 2:
                    handleWithdraw(wallet, acc);
                    break;
                case 3:
                    handleBalance(wallet, acc);
                    break;
                case 4:
                    handleConvert(wallet, acc);
                    break;
                case 5:
                    handleBalancesAll(wallet, acc);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
            }

            System.out.println(); // línea en blanco
        }

        System.out.println("Saliendo... 👋");
        sc.close();
    }

    private static void printMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("1) Depositar");
        System.out.println("2) Retirar");
        System.out.println("3) Consultar saldo (una moneda)");
        System.out.println("4) Convertir moneda");
        System.out.println("5) Ver saldos (todas las monedas)");
        System.out.println("0) Salir");
        System.out.println("--------------------------------------------------");
    }

    private static void handleDeposit(IWalletService wallet, Account acc) {
        Currency c = readCurrency("Moneda para depositar");
        BigDecimal amount = readAmount("Monto a depositar: ");

        try {
            wallet.deposit(acc, amount, c);
            System.out.println("✅ Depósito realizado.");
            System.out.println("Saldo " + c + ": " + wallet.getBalance(acc, c));
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void handleWithdraw(IWalletService wallet, Account acc) {
        Currency c = readCurrency("Moneda para retirar");
        BigDecimal amount = readAmount("Monto a retirar: ");

        try {
            wallet.withdraw(acc, amount, c);
            System.out.println("✅ Retiro realizado.");
            System.out.println("Saldo " + c + ": " + wallet.getBalance(acc, c));
        } catch (SaldoInsuficienteException e) {
            System.out.println("❌ Saldo insuficiente: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void handleBalance(IWalletService wallet, Account acc) {
        Currency c = readCurrency("Moneda a consultar");
        System.out.println("Saldo " + c + ": " + wallet.getBalance(acc, c));
    }

    private static void handleConvert(IWalletService wallet, Account acc) {
        Currency from = readCurrency("Moneda ORIGEN");
        Currency to = readCurrency("Moneda DESTINO");

        if (from == to) {
            System.out.println("❌ Moneda origen y destino no pueden ser iguales.");
            return;
        }

        BigDecimal amount = readAmount("Monto a convertir: ");

        try {
            wallet.convert(acc, amount, from, to);
            System.out.println("✅ Conversión realizada.");
            System.out.println("Saldo " + from + ": " + wallet.getBalance(acc, from));
            System.out.println("Saldo " + to + ": " + wallet.getBalance(acc, to));
        } catch (SaldoInsuficienteException e) {
            System.out.println("❌ Saldo insuficiente: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void handleBalancesAll(IWalletService wallet, Account acc) {
        for (Currency c : Currency.values()) {
            System.out.println("Saldo " + c + ": " + wallet.getBalance(acc, c));
        }
    }

    // -------- helpers de lectura --------

    private static int readInt(String label) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingresa un número válido.");
            }
        }
    }

    private static BigDecimal readAmount(String label) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine().trim().replace(",", "."); // por si escriben 10,5
            try {
                BigDecimal amount = new BigDecimal(input);
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("❌ El monto debe ser mayor que 0.");
                    continue;
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("❌ Monto inválido. Ej: 1000 o 1000.50");
            }
        }
    }

    private static Currency readCurrency(String label) {
        while (true) {
            System.out.println(label + " (elige una):");
            for (Currency c : Currency.values()) {
                System.out.println("- " + c.name());
            }
            System.out.print("Moneda: ");
            String input = sc.nextLine().trim().toUpperCase();

            try {
                return Currency.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Moneda inválida. Ej: CLP / USD / EUR");
            }
        }
    }
}
