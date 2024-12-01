package com.jesusalvarez.conversordemonedas;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.cdimascio.dotenv.Dotenv;

public class ExchangeRateApiClient {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("EXCHANGE_RATE_API_KEY");
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String APP_MODE = dotenv.get("APP_MODE");

    public JsonObject getLatestRates(String baseCurrency) throws Exception {
        String urlString = BASE_URL + API_KEY + "/latest/" + baseCurrency;
        URL url = new URL(urlString);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        if ("dev".equalsIgnoreCase(APP_MODE)) {
            System.out.println("Request URL: " + urlString);
            System.out.println("Response Code: " + request.getResponseCode());
        }

        InputStreamReader reader = new InputStreamReader(request.getInputStream());
        JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();

        if ("dev".equalsIgnoreCase(APP_MODE)) {
            System.out.println("Response JSON: " + jsonResponse.toString());
        }

        return jsonResponse;
    }
}