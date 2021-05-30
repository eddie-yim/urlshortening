package com.example.integration;

import com.example.application.url.UrlApplicationService;
import com.example.application.url.UrlFinalizingFailedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UrlApplicationServiceTest {
    @Autowired
    UrlApplicationService urlService;

    @Test
    public void testPersistenceAndDuplication() {
        String originalUrl = "naver.com";
        Pair<String, String> result = this.urlService.shorten(originalUrl);
        String finalizedOriginalUrl = result.getFirst();
        String shortenedUrl = result.getSecond();

        Assertions.assertEquals("https://www.naver.com/", finalizedOriginalUrl);
        Assertions.assertTrue(shortenedUrl.matches("[0-9A-Za-z]{1,8}"));

        Assertions.assertTrue(this.urlService.shortenedUrlExists(shortenedUrl));

        String originalFound = this.urlService.toOriginal(shortenedUrl);
        Assertions.assertEquals("https://www.naver.com/", originalFound);
        Assertions.assertEquals(finalizedOriginalUrl, originalFound);
    }

    @Test
    public void testMalformedUrl() {
        Assertions.assertThrows(UrlFinalizingFailedException.class, () -> {
            String malformedUrl = "//jklgdhgh5";
            this.urlService.shorten(malformedUrl);
        });
    }

    @Test
    public void testUnknownHostUrl() {
        Assertions.assertThrows(UrlFinalizingFailedException.class, () -> {
            String unknownHostUrl = "http://jklgdhgh5khkc.com";
            Pair<String, String> result = this.urlService.shorten(unknownHostUrl);
        });
    }
}
