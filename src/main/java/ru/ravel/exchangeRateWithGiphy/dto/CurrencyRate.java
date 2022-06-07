package ru.ravel.exchangeRateWithGiphy.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
public class CurrencyRate {
    private BigDecimal rate;

    public CurrencyRate(Map<String, Object> rawResult, String currencyCode) {
        this.rate = getRate(rawResult, currencyCode);
    }

    public static BigDecimal getRate(Map<String, Object> rawResult, String currencyCode) {
        Map<String, Object> rates = (Map<String, Object>) rawResult.get("rates");
        return new BigDecimal(rates.get(currencyCode).toString());
    }

}
