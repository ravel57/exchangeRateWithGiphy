package ru.ravel.exchangeRateWithGiphy.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@FeignClient(value = "getCurrency", url = "https://openexchangerates.org/api")
public interface OpenExchangeRatesClient {

    @GetMapping(value = "/historical/{date}.json")
    LinkedHashMap<String, Object> getCurrencyByDate(@PathVariable String date,
                                                    @RequestParam("app_id") String token);

    @GetMapping(value = "/latest.json")
    LinkedHashMap<String, Object> getLatestCurrency(@RequestParam("app_id") String token);
}