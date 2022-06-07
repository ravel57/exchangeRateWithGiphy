package ru.ravel.exchangeRateWithGiphy.services;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ravel.exchangeRateWithGiphy.clients.OpenExchangeRatesClient;
import ru.ravel.exchangeRateWithGiphy.dto.CurrencyRate;
import ru.ravel.exchangeRateWithGiphy.enums.ChangeOfCourse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class OpenExchangeRatesService {

    @Autowired
    OpenExchangeRatesClient ratesClient;

    static String token = System.getenv("openExchangeRatesToken");

    public ChangeOfCourse getTagByCourseChange(String currencyCode) {
        currencyCode = currencyCode.toUpperCase();
        String previousDay = LocalDate.now().minusDays(1).toString();

        try {
            BigDecimal todayRate = CurrencyRate.getRate(ratesClient.getLatestCurrency(token), currencyCode);
            BigDecimal yesterdayRate = CurrencyRate.getRate(ratesClient.getCurrencyByDate(token, previousDay), currencyCode);

            if (todayRate.compareTo(yesterdayRate) > 0)
                return ChangeOfCourse.higher;
            else if (todayRate.compareTo(yesterdayRate) == 0)
                return ChangeOfCourse.noChange;
            else
                return ChangeOfCourse.lower;
        } catch (FeignException e) {
            e.printStackTrace();
            throw e;
        } catch (NullPointerException e) {
            return ChangeOfCourse.error;
        }
    }

    public Map<String, Object> getCurrencies() {
        Map<String, Object> currencies = ratesClient.getCurrencies(token);
        return currencies;
    }
}
