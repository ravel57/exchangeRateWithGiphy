package ru.ravel.exchangeRateWithGiphy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ravel.exchangeRateWithGiphy.services.GiphyService;
import ru.ravel.exchangeRateWithGiphy.services.OpenExchangeRatesService;

import javax.servlet.http.HttpServletResponse;

@Controller
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
            ClassPathResource imgFile = giphyService.getClassPathResource(currencyCode);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
