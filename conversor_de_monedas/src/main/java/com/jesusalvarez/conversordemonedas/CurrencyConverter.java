package com.jesusalvarez.conversordemonedas;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CurrencyConverter {
    private ExchangeRateApiClient apiClient;
    private List<ConversionRecord> history;

    public CurrencyConverter() {
        this.apiClient = new ExchangeRateApiClient();
        this.history = new ArrayList<>();
    }

    public double convert(String fromCurrency, String toCurrency, double amount) throws Exception {
        JsonObject rates = apiClient.getLatestRates(fromCurrency);
        double rate = rates.getAsJsonObject("conversion_rates").get(toCurrency).getAsDouble();
        double result = amount * rate;
        history.add(new ConversionRecord(fromCurrency, toCurrency, amount, result));
        return result;
    }

    public Map<String, Double> getFilteredRates(String baseCurrency) throws Exception {
        JsonObject rates = apiClient.getLatestRates(baseCurrency).getAsJsonObject("conversion_rates");
        Map<String, Double> filteredRates = new HashMap<>();

        String[] selectedCurrencies = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};
        for (String currency : selectedCurrencies) {
            if (rates.has(currency)) {
                filteredRates.put(currency, rates.get(currency).getAsDouble());
            }
        }
        return filteredRates;
    }

    public List<ConversionRecord> getHistory() {
        return history;
    }

    public Set<String> getAvailableCurrencies(String baseCurrency) throws Exception {
        JsonObject rates = apiClient.getLatestRates(baseCurrency).getAsJsonObject("conversion_rates");
        return rates.keySet();
    }
}