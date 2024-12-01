package com.jesusalvarez.conversordemonedas;

import com.google.gson.JsonObject;


public class CurrencyConverter {
    private ExchangeRateApiClient apiClient;

    public CurrencyConverter() {
        this.apiClient = new ExchangeRateApiClient();
    }

    public double convert(String fromCurrency, String toCurrency, double amount) throws Exception {
        JsonObject rates = apiClient.getLatestRates(fromCurrency);
        double rate = rates.getAsJsonObject("conversion_rates").get(toCurrency).getAsDouble();
        return amount * rate;
    }
}
