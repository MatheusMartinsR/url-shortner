package com.matheus.url_shortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.url_shortner.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

}
