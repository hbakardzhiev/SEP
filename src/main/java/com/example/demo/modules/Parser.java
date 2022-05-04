package com.example.demo.modules;

import com.example.demo.repository.ParserRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Parser implements ParserRepository {
  private final Document document;

  public Parser() throws IOException {
    final var input = new File(getClass().getClassLoader().getResource("example.html").getFile());
    document = Jsoup.parse(input, "UTF-8");
  }

  public List<String> parseElement() {
    return List.of(
        document.select("[attrid=description]").text(),
        document.select("[attrid=phiChangeType]").text());
  }
}
