package com.example.demo.services;

import com.example.demo.modules.CN;
import com.example.demo.repository.ParserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserService {

  @Autowired
  private ParserRepository repository;

  public String parse() {
    return repository.parseElement().toString();
//    return new JSONObject();
  }
}
