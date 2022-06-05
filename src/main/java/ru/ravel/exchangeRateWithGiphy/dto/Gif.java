package ru.ravel.exchangeRateWithGiphy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class Gif {
    private String url;
    private String id;

    public Gif(LinkedHashMap<Object, Object> rawResult) {
        Map<String, Object> data = (Map<String, Object>) rawResult.get("data");
        Map<String, Object> images = (Map<String, Object>) data.get("images");
        Map<String, Object> original = (Map<String, Object>) images.get("original");
        url = original.get("url").toString();
        id = data.get("id").toString();
    }
}
