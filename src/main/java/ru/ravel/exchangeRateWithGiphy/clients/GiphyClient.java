package ru.ravel.exchangeRateWithGiphy.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;

@FeignClient(value = "getGif", url = "${giphy.url}")

public interface GiphyClient {

    @GetMapping(value = "")
    LinkedHashMap<Object, Object> getRandomGifByTag(@RequestParam("api_key") String token,
                                                    @RequestParam("tag") String tag);
}
