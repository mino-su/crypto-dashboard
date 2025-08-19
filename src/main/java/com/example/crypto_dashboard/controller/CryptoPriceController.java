package com.example.crypto_dashboard.controller;

import com.example.crypto_dashboard.model.crypto.CryptoPriceResponse;
import com.example.crypto_dashboard.model.currency.CurrencyResponse;
import com.example.crypto_dashboard.model.price.PriceResponse;
import com.example.crypto_dashboard.service.CurrencyMonitorService;
import com.example.crypto_dashboard.service.PriceMonitorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/crypto-prices")
public class CryptoPriceController {
    private final PriceMonitorService priceMonitorService;
    private final CurrencyMonitorService currencyMonitorService;


    @GetMapping("/{cryptoUnit}")
    public ResponseEntity<CryptoPriceResponse> getCryptoPrice(@PathVariable String cryptoUnit) {
        var priceResponse = priceMonitorService.getPrice(cryptoUnit);
        var currencyResponse = currencyMonitorService.getCurrency("USD");
        var cryptoPriceResponse =
                new CryptoPriceResponse(cryptoUnit, priceResponse.amount() * currencyResponse.rate());
        return ResponseEntity.ok(cryptoPriceResponse);
    }
}
