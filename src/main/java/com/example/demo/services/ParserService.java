package com.example.demo.services;

import com.example.demo.modules.SheetType;
import com.example.demo.repository.ParserRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserService {

  @Autowired private ParserRepository repository;

  public String parseCN() throws IOException {
    return repository.parseCN().toString();
  }

  public String parseCR() throws IOException {
    return repository.parseCR().toString();
  }

  public String parseCT() throws IOException {
    return repository.parseCT().toString();
  }
}
