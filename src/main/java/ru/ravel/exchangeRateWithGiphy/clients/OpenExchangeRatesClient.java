package ru.ravel.exchangeRateWithGiphy.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@FeignClient(value = "getCurrency", url = "${openexchangerate.url}")
public interface OpenExchangeRatesClient {

    @GetMapping(value = "/historical/{date}.json")
    LinkedHashMap<String, Object> getCurrencyByDate(@RequestParam("app_id") String token,
                                                    @PathVariable String date);

    @GetMapping(value = "/latest.json")
    LinkedHashMap<String, Object> getLatestCurrency(@RequestParam("app_id") String token);

    @GetMapping(value = "/currencies.json")
    LinkedHashMap<String, Object> getCurrencies(@RequestParam("app_id") String token);
}