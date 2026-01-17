# 💳 Alke Wallet — Evaluación Módulo 2

Proyecto desarrollado para la **Evaluación Integradora del Módulo 2** del programa **Android Trainee**.

La aplicación implementa una billetera digital (wallet) en Java que permite realizar operaciones financieras básicas utilizando arquitectura orientada a objetos, manejo de excepciones y pruebas unitarias automatizadas.

---

## 🚀 Funcionalidades principales

El sistema permite:

- ✅ Crear cuenta bancaria virtual
- ✅ Depositar dinero por tipo de moneda
- ✅ Retirar dinero validando saldo disponible
- ✅ Consultar saldo por moneda
- ✅ Consultar todos los saldos
- ✅ Convertir dinero entre monedas
- ✅ Manejar excepciones por saldo insuficiente
- ✅ Ejecutar pruebas unitarias con JUnit
- ✅ Interfaz por consola con menú interactivo

---

## 🛠 Tecnologías utilizadas

- Java 11 (Eclipse Temurin)
- Eclipse IDE
- JUnit 4
- Git y GitHub

---

## 📁 Estructura del proyecto

src/
└── cl.alke.wallet
├── app
│ └── Main.java
│
├── model
│ ├── Account.java
│ └── Currency.java
│
├── service
│ ├── IWalletService.java
│ └── WalletService.java
│
└── exception
└── SaldoInsuficienteException.java

test/
└── cl.alke.wallet.service
└── WalletServiceTest.java


---

## ▶ Cómo ejecutar la aplicación

### Ejecutar menú por consola

1. Abrir el proyecto en Eclipse
2. Ir a:

src/cl/alke/wallet/app/Main.java

3. Click derecho → **Run As → Java Application**

Se desplegará el menú interactivo en consola.

---

## 🧪 Cómo ejecutar pruebas unitarias (JUnit)

1. Ir a:

test/cl/alke/wallet/service/WalletServiceTest.java


2. Click derecho → **Run As → JUnit Test**

Resultado esperado:

Runs: 4
Errors: 0
Failures: 0


---

## 💱 Conversión de monedas (modo demostración)

Se utilizan tasas fijas para efectos académicos:

| Origen | Destino | Tasa |
--------|---------|------
USD | CLP | 900  
CLP | USD | 0.00111111  
EUR | CLP | 1000  
CLP | EUR | 0.001  
USD | EUR | 0.90  
EUR | USD | 1.11  

---

## 📟 Ejemplo de uso por consola

=== ALKE WALLET ===

Depositar

Retirar

Consultar saldo

Convertir moneda

Ver todos los saldos

Salir



---

## 👨‍💻 Autor

**Enrique Flores**  
GitHub: https://github.com/Crohnoz  

Proyecto desarrollado como parte del proceso formativo del programa Android Trainee.
