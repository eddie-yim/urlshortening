package com.example.application.url;

import org.springframework.data.util.Pair;

public interface UrlApplicationService {
    Pair<String, String> shorten(String originalUrl);

    String toOriginal(String shortenedUrl);

    boolean shortenedUrlExists(String shortenedUrl);
}
