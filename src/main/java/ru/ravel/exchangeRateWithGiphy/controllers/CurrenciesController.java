package ru.ravel.exchangeRateWithGiphy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.ravel.exchangeRateWithGiphy.services.GiphyService;
import ru.ravel.exchangeRateWithGiphy.services.OpenExchangeRatesService;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;

@RestController
public class CurrenciesController {

    @Autowired
    OpenExchangeRatesService ratesService;
    @Autowired
    GiphyService giphyService;

    @Value("${openexchangerates.defaultcurrency}")
    String defaultCurrency;


    @GetMapping
    public ResponseEntity<Object> getGifByDefaultCurrencyCode(HttpServletResponse response) {
        return getGifByCurrencyCode(defaultCurrency, response);
    }


    @GetMapping("/currencies")
    public ResponseEntity<Object> getCurrenciesCodes() {
        return ResponseEntity.status(HttpStatus.OK).body(ratesService.getCurrencies());
    }


    @GetMapping(value = "/currencies/{currencyCode}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Object> getGifByCurrencyCode(@PathVariable String currencyCode,
                                                       HttpServletResponse response) {
        try {
            Path imgFile = giphyService.getFile(currencyCode);
            giphyService.uploadGif(response, imgFile);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
