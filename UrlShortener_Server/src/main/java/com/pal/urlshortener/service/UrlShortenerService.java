package com.pal.urlshortener.service;

import com.pal.urlshortener.exception.UrlShortenerException;

public interface UrlShortenerService {
    String getLongUrl(String shortUrl) throws UrlShortenerException;
    String saveUrl(String longUrl);
}
