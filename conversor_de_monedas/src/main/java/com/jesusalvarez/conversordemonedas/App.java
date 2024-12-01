package com.jesusalvarez.conversordemonedas;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CurrencyConverter converter = new CurrencyConverter();

            while (true) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Convertir moneda");
                System.out.println("2. Salir");
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (option == 2) {
                    System.out.println("Gracias por usar el conversor de monedas.");
                    break;
                }

                System.out.println("Ingrese la moneda de origen (p. ej., USD):");
                String fromCurrency = scanner.nextLine().toUpperCase();

                System.out.println("Ingrese la moneda de destino (p. ej., EUR):");
                String toCurrency = scanner.nextLine().toUpperCase();

                System.out.println("Ingrese la cantidad a convertir:");
                double amount = scanner.nextDouble();

                try {
                    double result = converter.convert(fromCurrency, toCurrency, amount);
                    System.out.printf("%.2f %s son %.2f %s%n", amount, fromCurrency, result, toCurrency);
                } catch (Exception e) {
                    System.err.println("Error al realizar la conversión: " + e.getMessage());
                }
            }
        }
    }
}