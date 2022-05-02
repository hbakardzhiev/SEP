package com.example.demo.modules;

import com.example.demo.repository.ParserRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class Parser implements ParserRepository {
  private Document document;

  public Parser() throws IOException {
    var input = new File(getClass().getClassLoader().getResource("test.html").getFile());
    document = Jsoup.parse(input, "UTF-8");
  }

  public String parseElement() {
    return document.body().getAllElements().first().text();
  }
}
