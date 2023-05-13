package com.anilcan.urlshortener.model.response;

import java.util.List;

public record UrlListResponse(List<UrlResponse> allUrls) {
}
