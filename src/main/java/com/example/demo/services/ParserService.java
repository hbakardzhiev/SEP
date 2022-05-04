package com.example.demo.services;

import com.example.demo.modules.CN;
import com.example.demo.repository.ParserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ParserService {

  @Autowired private ParserRepository repository;

  public CN parse() {
    try {
      return repository.parseElement();
    } catch (Exception ex) {
      System.out.println("problem");
    }
    return new CN();
  }
}
