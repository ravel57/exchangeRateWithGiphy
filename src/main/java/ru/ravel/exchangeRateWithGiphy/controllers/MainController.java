package ru.ravel.exchangeRateWithGiphy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.ravel.exchangeRateWithGiphy.services.GiphyService;
import ru.ravel.exchangeRateWithGiphy.services.OpenExchangeRatesService;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class MainController {

    @Autowired
    OpenExchangeRatesService ratesService;
    @Autowired
    GiphyService giphyService;


    @GetMapping("/")
    public ResponseEntity<Object> rootGetMapping() {
        return ResponseEntity.status(HttpStatus.OK).body(ratesService.getCurrencies());
    }


    @GetMapping(value = "/{currencyCode}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Object> currencyCode(@PathVariable String currencyCode,
                                               HttpServletResponse response) {
        try {
            Path imgFile = giphyService.getFile(currencyCode);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            Files.copy(imgFile, response.getOutputStream());
            imgFile.toFile().delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
