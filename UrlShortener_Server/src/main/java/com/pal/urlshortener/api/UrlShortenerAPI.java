package com.pal.urlshortener.api;

import com.pal.urlshortener.exception.UrlShortenerException;
import com.pal.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping(value = "/")
@RestController
public class UrlShortenerAPI {

    @Autowired
    UrlShortenerService urlShortenerService;
    @Autowired
    Environment environment;

    @GetMapping(value = "/{code}")
    public ResponseEntity<String> getLongUrl(@PathVariable String code) throws UrlShortenerException, URISyntaxException {
        String longUrl=urlShortenerService.getLongUrl(code);
        URI uri=new URI(longUrl);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.TEMPORARY_REDIRECT);
    }

    @PostMapping(value = "/generate")
    public ResponseEntity<String> saveLongUrl(@RequestBody String longUrl) throws UrlShortenerException {
        String id=urlShortenerService.saveUrl(longUrl);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
