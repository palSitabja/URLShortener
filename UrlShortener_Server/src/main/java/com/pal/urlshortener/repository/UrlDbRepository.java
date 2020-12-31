package com.pal.urlshortener.repository;

import com.pal.urlshortener.entity.UrlData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlDbRepository extends MongoRepository<UrlData,String> {
    Optional<UrlData> findByShortUrl(String longUrl);
    Optional<UrlData> findByLongUrl(String longUrl);
}
