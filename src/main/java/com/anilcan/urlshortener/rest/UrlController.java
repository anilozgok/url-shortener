package com.anilcan.urlshortener.rest;

import com.anilcan.urlshortener.model.dto.UrlDto;
import com.anilcan.urlshortener.model.request.NewShortUrlRequest;
import com.anilcan.urlshortener.model.response.LongUrlResponse;
import com.anilcan.urlshortener.model.response.UrlResponse;
import com.anilcan.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/url/shortener/")
public class UrlController {

    private final UrlService urlService;
    private final HttpServletResponse response;


    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenShortUrl(@RequestBody NewShortUrlRequest newShortUrlRequest) {
        log.info("createShortUrl method caught newShortUrlRequest: " + newShortUrlRequest);
        var shortenedUrl = urlService.shortenUrl(new UrlDto(newShortUrlRequest.longUrl()));
        return new ResponseEntity<>(new UrlResponse(shortenedUrl.left, shortenedUrl.middle, shortenedUrl.right), HttpStatus.OK);
    }

    @GetMapping("/get/{shortUrl}")
    public ResponseEntity<LongUrlResponse> getLongUrlByShortUrl(@PathVariable String shortUrl) {
        log.info("getLongUrlByShortUrl method caught shortUrl: " + shortUrl);
        var urlDto = urlService.getURLByShortURL(shortUrl);
        return new ResponseEntity<>(new LongUrlResponse(urlDto.longUrl()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{shortUrl}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortUrl) {
        log.info("deleteUrl method caught shortUrl: " + shortUrl);
        urlService.deleteShortUrl(shortUrl);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/redirect/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) throws IOException {
        log.info("redirect method caught with shortUrl: " + shortUrl);
        var longUrl = urlService.getURLByShortURL(shortUrl).longUrl();
        response.sendRedirect(longUrl);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
