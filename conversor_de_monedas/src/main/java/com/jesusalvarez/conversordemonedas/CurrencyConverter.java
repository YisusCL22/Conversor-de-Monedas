package com.jesusalvarez.conversordemonedas;

import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Double> getFilteredRates(String baseCurrency) throws Exception {
        JsonObject rates = apiClient.getLatestRates(baseCurrency).getAsJsonObject("conversion_rates");
        Map<String, Double> filteredRates = new HashMap<>();

        // Lista de códigos de monedas a filtrar
        String[] selectedCurrencies = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};
        for (String currency : selectedCurrencies) {
            if (rates.has(currency)) {
                filteredRates.put(currency, rates.get(currency).getAsDouble());
            }
        }
        return filteredRates;
    }
}
