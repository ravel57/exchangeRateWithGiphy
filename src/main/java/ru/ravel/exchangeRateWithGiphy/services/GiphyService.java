package ru.ravel.exchangeRateWithGiphy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ravel.exchangeRateWithGiphy.dto.Gif;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GiphyService {

    @Autowired
    GiphyClient giphyClient;
    @Autowired
    OpenExchangeRatesService ratesService;

    @Value("${imagesDirPath}")
    String imagesDirPath;

    public Path getFile(String currencyCode) throws IOException {
        String tag = ratesService.isHigherThanYesterday(currencyCode) ? "rich" : "broke";
        String token = System.getenv("giphyToken");
        Gif data = new Gif(giphyClient.getGifByTag(token, tag));
        String imageUrl = new StringBuilder("https://i").append(data.getUrl(),
                "https://media".length() + 1,
                data.getUrl().length() - "https://media".length()
        ).toString();
        if (!Files.isDirectory(Paths.get(imagesDirPath)))
            new File(imagesDirPath).mkdirs();
        String name = String.format("%s/%s.gif", imagesDirPath, data.getId());
        saveImage(imageUrl,name);
        return Paths.get(name);
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
