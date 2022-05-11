package com.example.demo.services;

import com.example.demo.repository.ParserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserService {

  @Autowired
  private ParserRepository repository;

  public String parseElements() {
    return repository.parseElements().toString();
//    return new JSONObject();
  }

  public String parseCR() {
    return repository.parseCR();
  }
}
