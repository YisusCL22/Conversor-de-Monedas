package com.jesusalvarez.conversordemonedas;

import java.time.LocalDateTime;

public class ConversionRecord {
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double result;
    private LocalDateTime timestamp;

    public ConversionRecord(String fromCurrency, String toCurrency, double amount, double result) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[%s] %.2f %s = %.2f %s", timestamp, amount, fromCurrency, result, toCurrency);
    }
}