package com.anilcan.urlshortener.repository;

import com.anilcan.urlshortener.model.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> getURLByShortURL(String shortUrl);
}
