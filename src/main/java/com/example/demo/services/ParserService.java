package com.example.demo.services;

import com.example.demo.modules.ParserCN;
import com.example.demo.modules.ParserCR;
import com.example.demo.modules.ParserCT;
import com.example.demo.repository.SheetSourceRepository;
import java.io.IOException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserService {

  @Autowired
  protected SheetSourceRepository sheetSourceRepository;

  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCN() throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cNparser = new ParserCN("Change Notice - Example.html").parsePage(sheetSourceStream)
        .collect(Collectors.toList());
    return cNparser;
  }

  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCR() throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cnParser = new ParserCN("Change Notice - Example.html");
    final var crParser = new ParserCR(cnParser);
    return crParser.parsePage(sheetSourceStream).collect(Collectors.toList());
  }

  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCT() throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cnParser = new ParserCN("Change Notice - Example.html");
    final var ctParser = new ParserCT(cnParser);
    return ctParser.parsePage(sheetSourceStream).collect(Collectors.toList());
  }
}
