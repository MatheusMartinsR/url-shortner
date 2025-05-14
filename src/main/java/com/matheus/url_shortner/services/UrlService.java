package com.matheus.url_shortner.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.url_shortner.model.Url;
import com.matheus.url_shortner.repository.UrlRepository;

@Service
public class UrlService {

  @Autowired
  private UrlRepository urlRepository;

  public String shortenUrl(String original_url) {

    String shortUrl = generateShortUrl();
    Url url = new Url();
    url.setOriginal_url(original_url);
    url.setShort_url(shortUrl);
    url.setExpirationDate(LocalDateTime.now().plusDays(30));
    urlRepository.save(url);

    return shortUrl;
  }

  public String generateShortUrl() {
    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    StringBuilder shortUrl = new StringBuilder();
    Random random = new Random();
    int length = 5 + random.nextInt(6);
    for (int i = 0; i < length; i++) {
      shortUrl.append(caracteres.charAt(random.nextInt(caracteres.length())));
    }
    return shortUrl.toString();
  }
}
