package com.example.crypto_dashboard.service;


import com.example.crypto_dashboard.model.currency.CurrencyResponse;
import com.example.crypto_dashboard.model.price.PriceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CurrencyMonitorService {

    private final String apiUri;

    public CurrencyMonitorService(@Value("${currency-monitor.api-uri}") String apiUri) {
        this.apiUri = apiUri;
    }

    private final RestClient restClient = RestClient.create();


    public CurrencyResponse getCurrency(String currency) {
        return restClient
                .get()
                .uri(apiUri,currency)
                .retrieve()
                .body(CurrencyResponse.class);
    }
}
