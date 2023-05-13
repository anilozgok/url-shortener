package com.anilcan.urlshortener.service;

import com.anilcan.urlshortener.exception.InvalidUrlException;
import com.anilcan.urlshortener.exception.UrlNotFoundException;
import com.anilcan.urlshortener.model.dto.UrlDto;
import com.anilcan.urlshortener.model.entity.Url;
import com.anilcan.urlshortener.repository.UrlRepository;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public ImmutableTriple<String, String, LocalDateTime> shortenUrl(UrlDto urlDto) {

        log.info("shortenUrl service caught with urlDto: " + urlDto);

        if (!isUrlValid(urlDto.longUrl())) {
            log.info("Invalid url. Service will throw InvalidUrlException.");
            throw new InvalidUrlException();
        }

        var urlToSave = Url.builder()
                .longURL(urlDto.longUrl())
                .shortURL(encodeUrl(urlDto.longUrl()))
                .createdAt(LocalDateTime.now())
                .build();
        log.info("Url shortened.");

        var savedUrl = urlRepository.save(urlToSave);
        log.info("Short url saved to database.");

        return ImmutableTriple.of(savedUrl.getLongURL(), savedUrl.getShortURL(), savedUrl.getCreatedAt());
    }

    public UrlDto getURLByShortURL(String shortUrl) {
        log.info("getURLByShortURL service caught with shortUrl: " + shortUrl);
        return new UrlDto(getURLObjectByShortURL(shortUrl).getLongURL());
    }

    public void deleteShortUrl(String shortUrl) {
        log.info("deleteShortUrl service caught with shortUrl: " + shortUrl);
        var urlToDelete = getURLObjectByShortURL(shortUrl);
        urlRepository.delete(urlToDelete);
    }

    private Url getURLObjectByShortURL(String shortUrl) {
        log.info("getURLObjectByShortURL service caught with shortUrl: " + shortUrl);
        return urlRepository.getURLByShortURL(shortUrl).orElseThrow(UrlNotFoundException::new);
    }

    private boolean isUrlValid(String url) {
        log.info("isUrlValid method processing with url: " + url);
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    private String encodeUrl(String url) {
        log.info("encodeUrl method processing with url: " + url);
        LocalDateTime time = LocalDateTime.now();
        return Hashing.murmur3_32_fixed()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
    }
}
