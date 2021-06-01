package com.example.application.url;

import com.example.common.Base62;
import com.example.domain.url.Url;
import com.example.domain.url.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UrlApplicationServiceImpl implements UrlApplicationService {

    private final UrlRepository urlRepository;

    public UrlApplicationServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Transactional
    @Override
    public Pair<String, String> shorten(String originalUrl) {
        String finalizedOriginalUrl = "";
        try {
            finalizedOriginalUrl = UrlFinalizer.finalize(originalUrl);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UrlFinalizingFailedException(e);
        }

        Url url = this.urlRepository
                .findByOriginal(finalizedOriginalUrl)
                .orElse(new Url(finalizedOriginalUrl));

        url.increaseCount();
        this.urlRepository.save(url);

        log.debug("URL: {}, COUNT: {}", url.getOriginal(), url.getCount());

        return Pair.of(finalizedOriginalUrl, Base62.encode(url.getId()));
    }

    @Override
    public String toOriginal(String shortenedUrl) {
        long id = Base62.decode(shortenedUrl);
        Url url = this.urlRepository
                .findById(id)
                .orElseThrow(UnknownShortenedUrlException::new);
        return url.getOriginal();
    }

    @Override
    public boolean shortenedUrlExists(String shortenedUrl) {
        long id = Base62.decode(shortenedUrl);
        return this.urlRepository.existsById(id);
    }
}
