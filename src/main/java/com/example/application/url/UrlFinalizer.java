package com.example.application.url;

import java.net.HttpURLConnection;
import java.net.URL;

public class UrlFinalizer {

    public static String finalize(String originalUrl) throws Exception {
        if (!originalUrl.startsWith("http")) {
            originalUrl = "http://" + originalUrl;
        }

        URL url = new URL(originalUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setInstanceFollowRedirects(true);

        int responseCode = conn.getResponseCode();
        switch (responseCode / 100) {
            case 2:
                return conn.getURL().toString();
            case 3:
                return conn.getHeaderField("Location");
            default:
                throw new UrlFinalizingFailedException("Failed to finalize url");
        }
    }
}
