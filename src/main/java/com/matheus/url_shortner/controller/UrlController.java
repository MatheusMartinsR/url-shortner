package com.matheus.url_shortner.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.url_shortner.model.Url;
import com.matheus.url_shortner.services.UrlService;

@RestController
@RequestMapping("/url")
public class UrlController {

  @Autowired
  private UrlService urlService;

  @PostMapping("/shorten")
  public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody Map<String, String> request) {
    String originalUrl = request.get("url");
    String shortUrl = urlService.shortenUrl(originalUrl);
    Map<String, String> response = new HashMap<String, String>();
    response.put("url", "https://xxx.com/" + shortUrl);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{shortUrl}")
  public ResponseEntity<Object> redirectUrl(@PathVariable String shortUrl) {
    Optional<Url> urlOptional = urlService.getOriginalUrl(shortUrl);

    if (urlOptional.isPresent()) {
      String originalUrl = urlOptional.get().getOriginal_url();
      return ResponseEntity.status(HttpStatus.FOUND).header("Location", originalUrl).build();
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL not founded");

  }
}
