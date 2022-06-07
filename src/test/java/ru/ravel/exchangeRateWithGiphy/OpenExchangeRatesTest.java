package ru.ravel.exchangeRateWithGiphy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ravel.exchangeRateWithGiphy.enums.ChangeOfCourse;
import ru.ravel.exchangeRateWithGiphy.services.OpenExchangeRatesService;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OpenExchangeRatesTest {

    @Autowired
    private OpenExchangeRatesService openExchangeRatesService;


    @Test
    void isTokenExistTest() {
        Assert.assertNotNull(System.getenv("openExchangeRatesToken"));
    }


    @Test
    void isHigherThanYesterdayTest() {
        ChangeOfCourse changeOfCourse = openExchangeRatesService.getTagByCourseChange("USD");
        Assert.assertEquals(ChangeOfCourse.noChange, changeOfCourse);
    }

    @Test
    void getCurrenciesTest() {
        Map<String, Object> currencies = openExchangeRatesService.getCurrencies();
        Assert.assertTrue(currencies.containsKey("USD"));
        Assert.assertTrue(currencies.containsKey("RUB"));
        Assert.assertTrue(currencies.containsKey("UAH"));
    }


}
