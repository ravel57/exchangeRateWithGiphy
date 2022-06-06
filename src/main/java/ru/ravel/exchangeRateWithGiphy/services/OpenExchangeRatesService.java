package ru.ravel.exchangeRateWithGiphy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ravel.exchangeRateWithGiphy.clients.OpenExchangeRatesClient;
import ru.ravel.exchangeRateWithGiphy.enums.ChangeOfCourse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class OpenExchangeRatesService {

    @Autowired
    OpenExchangeRatesClient ratesClient;

    public ChangeOfCourse isHigherThanYesterday(String currencyCode) {
        String token = System.getenv("openExchangeRatesToken");
        currencyCode = currencyCode.toUpperCase();
        String previousDay = LocalDate.now().minusDays(1).toString();

        try {
            Map<String, Object> todayRates = (Map<String, Object>) ratesClient.getLatestCurrency(token).get("rates");
            BigDecimal todayRate = new BigDecimal(todayRates.get(currencyCode).toString());

            Map<String, Object> yesterdayRates = (Map<String, Object>) ratesClient.getCurrencyByDate(token, previousDay).get("rates");
            BigDecimal yesterdayRate = new BigDecimal(yesterdayRates.get(currencyCode).toString());

            if (todayRate.compareTo(yesterdayRate) > 0)
                return ChangeOfCourse.higher;
            else if (todayRate.compareTo(yesterdayRate) == 0)
                return ChangeOfCourse.noChange;
            else
                return ChangeOfCourse.lower;

        } catch (NullPointerException e) {
            return ChangeOfCourse.error;
        }
    }

    public Map<String, Object>  getCurrencies() {
        String token = System.getenv("openExchangeRatesToken");
        Map<String, Object> currencies = ratesClient.getCurrencies(token);
        return currencies;
    }
}
