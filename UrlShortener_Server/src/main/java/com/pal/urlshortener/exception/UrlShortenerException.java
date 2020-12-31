package com.pal.urlshortener.exception;

import java.util.function.Supplier;

public class UrlShortenerException extends Exception {
    public UrlShortenerException(String exception){
        super(exception);
    }
}
