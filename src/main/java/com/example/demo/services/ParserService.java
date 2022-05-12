package com.example.demo.services;

import com.example.demo.modules.SheetType;
import com.example.demo.repository.ParserRepository;
import java.io.IOException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserService {

  @Autowired
  private ParserRepository repository;

  public String parseElements() {
    return repository.parseElements().toString();
  }

  public String parseCR(SheetType sheetTypeEnum) throws IOException {
    return repository.parseCR(sheetTypeEnum).toString();
  }
}
