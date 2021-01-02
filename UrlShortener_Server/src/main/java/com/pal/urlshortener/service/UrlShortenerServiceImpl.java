package com.pal.urlshortener.service;

import com.pal.urlshortener.entity.UrlData;
import com.pal.urlshortener.exception.UrlShortenerException;
import com.pal.urlshortener.repository.UrlDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service("UrlShortenerService")
@Transactional
public class UrlShortenerServiceImpl implements UrlShortenerService{
    private Integer base=62;
    @Autowired
    UrlDbRepository urlDbRepository;

    @Override
    public String getLongUrl(String shortUrl) throws UrlShortenerException {
        Optional<UrlData> urlDataDB=urlDbRepository.findByShortUrl(shortUrl);
        UrlData urlData=urlDataDB.orElseThrow(()->new UrlShortenerException(""));
        //urlDbRepository.findById()
        return urlData.getLongUrl();
    }

    @Override
    public String saveUrl(String longUrl) {
        Optional<UrlData> urlDataDB=urlDbRepository.findByLongUrl(longUrl);
        UrlData urlData=new UrlData();
        if(urlDataDB.isPresent()){
            urlData=urlDataDB.get();
            return urlData.getShortUrl();
        }
        String encodedURL=new String(urlEncoder());
        urlData.setLongUrl(longUrl.strip());
        urlData.setShortUrl(encodedURL);
        urlData.setTimestamp(LocalDateTime.now());
        UrlData urlDataSaved=urlDbRepository.save(urlData);
        return urlDataSaved.getShortUrl();
    }

    public StringBuilder base62converter(Long number){
        String base62="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        List<Integer> base62List=new ArrayList<>();
        while (number>0){
            Integer rem= (int) (number%base);
            base62List.add(rem);
            number/=base;
        }
        Collections.reverse(base62List);
        StringBuilder enccodedOp=new StringBuilder();
        base62List.forEach(val->{
            enccodedOp.append(base62.charAt(val));
        });
        return enccodedOp;
    }

    public StringBuilder urlEncoder(){

        String[] timeStamp=LocalDateTime.now().toString().split("-");
        String val=String.join("",timeStamp);
        timeStamp=val.split(":");
        val=String.join("",timeStamp);
        timeStamp=val.split("[.]");
        val=String.join("",timeStamp);
        timeStamp=val.split("T");
        val=String.join("",timeStamp).substring(2,15);

        StringBuilder enccodedURL=base62converter(Long.valueOf(val));
        return enccodedURL;
    }

}
