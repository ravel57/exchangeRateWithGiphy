package ru.ravel.exchangeRateWithGiphy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ravel.exchangeRateWithGiphy.services.GiphyService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GiphyServiceTest {

    @Autowired
    GiphyService giphyService;

    @Test
    void isTokenExistTest() {
        Assert.assertNotNull(System.getenv("giphyToken"));
    }

    @Test
    void isFileSaved() throws IOException {
        Path path = giphyService.getFile("USD");
        Assert.assertTrue(Files.exists(path));
        Assert.assertTrue(path.toFile().delete());
    }

}
