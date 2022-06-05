package ru.ravel.exchangeRateWithGiphy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class OpenExchangeRatesService {

    @Autowired
    OpenExchangeRatesClient ratesClient;

    public boolean isHigherThanYesterday(String currencyCode) {
        String token = System.getenv("openExchangeRatesToken");
        currencyCode = currencyCode.toUpperCase();

        String previousDay = LocalDate.now().minusDays(1).toString();

        Map<String, Object> yesterdayRates = (Map<String, Object>) ratesClient.getCurrencyByDate(previousDay, token).get("rates");
        BigDecimal yesterdayRate = new BigDecimal(yesterdayRates.get(currencyCode).toString());

        Map<String, Object> todayRates = (Map<String, Object>) ratesClient.getLatestCurrency(token).get("rates");
        BigDecimal todayRate = new BigDecimal(todayRates.get(currencyCode).toString());

        return todayRate.compareTo(yesterdayRate) >= 0;
    }
}
