package ru.ravel.exchangeRateWithGiphy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.ravel.exchangeRateWithGiphy.dto.Gif;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

@Service
public class GiphyService {

    @Autowired
    GiphyClient giphyClient;
    @Autowired
    OpenExchangeRatesService ratesService;

    public ClassPathResource getClassPathResource(String currencyCode) throws IOException {
        String tag = ratesService.isHigherThanYesterday(currencyCode) ? "rich" : "broke";
        String token = System.getenv("giphyToken");
        Gif data = new Gif(giphyClient.getGifByTag(token, tag));
        String imageUrl = new StringBuilder("https://i").append(data.getUrl(),
                "https://media".length() + 1,
                data.getUrl().length() - "https://media".length()
        ).toString();
        String name = String.format("images/%s.gif", data.getId());
        saveImage(imageUrl, name);
        return new ClassPathResource(name);
    }


    private void saveImage(String imageUrl, String destinationFile) throws IOException {
        InputStream is = new URL(imageUrl).openStream();
        OutputStream os = new FileOutputStream(destinationFile);
        byte[] b = new byte[2048];
        int length;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
    }
}
