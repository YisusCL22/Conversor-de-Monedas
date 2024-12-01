package com.jesusalvarez.conversordemonedas;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class App {
    // Códigos ANSI para colores
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CurrencyConverter converter = new CurrencyConverter();

            while (true) {
                clearScreen();
                printHeader("Conversor de Monedas");

                System.out.println(CYAN + "Seleccione una opción:" + RESET);
                System.out.println(GREEN + "1. Convertir moneda" + RESET);
                System.out.println(GREEN + "2. Mostrar tasas de cambio filtradas (ARS, BOB, BRL, CLP, COP, USD)" + RESET);
                System.out.println(GREEN + "3. Mostrar historial de conversiones" + RESET);
                System.out.println(GREEN + "4. Mostrar todas las monedas disponibles" + RESET);
                System.out.println(GREEN + "5. Salir" + RESET);
                System.out.print(YELLOW + "Ingrese su opción: " + RESET);

                if (!scanner.hasNextInt()) {
                    System.out.println(RED + "Entrada inválida. Por favor, ingrese un número entre 1 y 5." + RESET);
                    scanner.nextLine(); // Limpiar entrada inválida
                    pressEnterToContinue(scanner);
                    continue;
                }

                int option = scanner.nextInt();
                scanner.nextLine(); // Consumir newline

                if (option == 5) {
                    System.out.println(GREEN + "Gracias por usar el conversor de monedas." + RESET);
                    break;
                }

                switch (option) {
                    case 1:
                        convertirMoneda(scanner, converter);
                        break;

                    case 2:
                        mostrarTasasFiltradas(scanner, converter);
                        break;

                    case 3:
                        mostrarHistorial(converter);
                        break;

                    case 4:
                        mostrarMonedasDisponibles(scanner, converter);
                        break;

                    default:
                        System.out.println(RED + "Opción no válida. Intente nuevamente." + RESET);
                        break;
                }

                pressEnterToContinue(scanner);
            }
        }
    }

    private static void convertirMoneda(Scanner scanner, CurrencyConverter converter) {
        System.out.println(CYAN + "\n--- Convertir Moneda ---" + RESET);
        System.out.print(YELLOW + "Ingrese la moneda de origen (p. ej., USD): " + RESET);
        String fromCurrency = scanner.nextLine().toUpperCase();

        System.out.print(YELLOW + "Ingrese la moneda de destino (p. ej., EUR): " + RESET);
        String toCurrency = scanner.nextLine().toUpperCase();

        System.out.print(YELLOW + "Ingrese la cantidad a convertir: " + RESET);
        if (!scanner.hasNextDouble()) {
            System.out.println(RED + "Cantidad inválida. Por favor, ingrese un número." + RESET);
            scanner.nextLine(); // Limpiar entrada inválida
            return;
        }
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consumir newline

        try {
            double result = converter.convert(fromCurrency, toCurrency, amount);
            System.out.println(GREEN + String.format("\n%.2f %s son %.2f %s%n", amount, fromCurrency, result, toCurrency) + RESET);
        } catch (Exception e) {
            System.err.println(RED + "Error al realizar la conversión: " + e.getMessage() + RESET);
        }
    }

    private static void mostrarTasasFiltradas(Scanner scanner, CurrencyConverter converter) {
        System.out.println(CYAN + "\n--- Mostrar Tasas de Cambio Filtradas ---" + RESET);
        System.out.print(YELLOW + "Ingrese la moneda base (p. ej., USD): " + RESET);
        String baseCurrency = scanner.nextLine().toUpperCase();

        try {
            Map<String, Double> rates = converter.getFilteredRates(baseCurrency);
            System.out.println(GREEN + "\nTasas de cambio filtradas:" + RESET);
            rates.forEach((currency, rate) ->
                System.out.println(PURPLE + currency + ": " + String.format("%.2f", rate) + RESET)
            );
        } catch (Exception e) {
            System.err.println(RED + "Error al obtener las tasas de cambio: " + e.getMessage() + RESET);
        }
    }

    private static void mostrarHistorial(CurrencyConverter converter) {
        System.out.println(CYAN + "\n--- Historial de Conversiones ---" + RESET);
        List<ConversionRecord> history = converter.getHistory();
        if (history.isEmpty()) {
            System.out.println(YELLOW + "No hay conversiones realizadas." + RESET);
        } else {
            history.forEach(record -> System.out.println(PURPLE + record.toString() + RESET));
        }
    }

    private static void mostrarMonedasDisponibles(Scanner scanner, CurrencyConverter converter) {
        System.out.println(CYAN + "\n--- Mostrar Todas las Monedas Disponibles ---" + RESET);
        System.out.print(YELLOW + "Ingrese la moneda base para listar todas las monedas disponibles: " + RESET);
        String base = scanner.nextLine().toUpperCase();
    
        try {
            Set<String> currencies = converter.getAvailableCurrencies(base);
            System.out.println(GREEN + "\nMonedas disponibles: " + String.join(", ", currencies) + RESET);
        } catch (Exception e) {
            System.err.println(RED + "Error al obtener las monedas disponibles: " + e.getMessage() + RESET);
        }
    }

    private static void printHeader(String title) {
        System.out.println(BLUE + "========================================" + RESET);
        System.out.println(BLUE + "          " + title + "          " + RESET);
        System.out.println(BLUE + "========================================\n" + RESET);
    }

    private static void pressEnterToContinue(Scanner scanner) {
        System.out.print(YELLOW + "\nPresione Enter para continuar..." + RESET);
        scanner.nextLine();
    }

    private static void clearScreen() {
        // Intenta limpiar la consola (funciona en la mayoría de los sistemas)
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Si no se puede limpiar, simplemente continua
        }
    }
}