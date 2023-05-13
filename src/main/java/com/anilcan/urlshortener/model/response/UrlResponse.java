package com.anilcan.urlshortener.model.response;

import java.time.LocalDateTime;

public record UrlResponse(String longUrl, String shortUrl, LocalDateTime createdAt) {
}
