package ru.ravel.exchangeRateWithGiphy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OpenExchangeRatesService {

    @Autowired
    OpenExchangeRatesClient ratesClient;

    public boolean isHigherThanYesterday(String currencyCode) {
        String token = System.getenv("openExchangeRatesToken");
        currencyCode = currencyCode.toUpperCase();

        String previousDay = LocalDate.now().minusDays(1).toString();

        Map<String, Object> yesterdayRates = (Map<String, Object>) ratesClient.getCurrencyByDate(token, previousDay).get("rates");
        BigDecimal yesterdayRate = new BigDecimal(yesterdayRates.get(currencyCode).toString());

        Map<String, Object> todayRates = (Map<String, Object>) ratesClient.getLatestCurrency(token).get("rates");
        BigDecimal todayRate = new BigDecimal(todayRates.get(currencyCode).toString());

        return todayRate.compareTo(yesterdayRate) >= 0;
    }

    public List<String> getCurrencies() {
        String token = System.getenv("openExchangeRatesToken");
        Map<String, Object> currencies = ratesClient.getCurrencies(token);
        return currencies.entrySet().stream()
                .map(el -> String.format("%s - %s", el.getKey(), el.getValue().toString()))
                .collect(Collectors.toList());
    }
}
