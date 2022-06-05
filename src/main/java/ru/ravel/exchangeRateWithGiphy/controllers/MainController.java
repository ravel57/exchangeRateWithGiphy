package ru.ravel.exchangeRateWithGiphy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ravel.exchangeRateWithGiphy.services.OpenExchangeRatesService;

@Controller
public class MainController {

    @Autowired
    OpenExchangeRatesService openExchangeRatesService;


    @GetMapping("/")
    public void rootGetMapping() {
    }

    @GetMapping("/{currencyCode}")
    public ResponseEntity<Object> currencyCode(@PathVariable("currencyCode") String currencyCode) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(openExchangeRatesService.isHigherThanYesterday(currencyCode));
    }

}
