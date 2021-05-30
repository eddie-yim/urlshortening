package com.example.domain.url;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Long> {
    Optional<Url> findByOriginal(String original);
}
