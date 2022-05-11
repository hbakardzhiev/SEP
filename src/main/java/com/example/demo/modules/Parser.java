package com.example.demo.modules;

import com.example.demo.repository.CNRepository;
import com.example.demo.repository.ParserRepository;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

public class Parser implements ParserRepository {

  private final Document document;


  @Autowired
  private CNRepository cnRepository;


  public Parser(String startUrl) throws IOException {
    final var input = new File(getClass().getClassLoader().getResource(startUrl).getFile());
    document = Jsoup.parse(input, "UTF-8");
  }

  private String parseElementByTag(String tag, String id) {
    return this.document.select(String.format("[%s=%s]", tag, id)).text();
  }

  public JSONObject parseElements() {
    var jsonObject = new JSONObject();
    cnRepository.findAll().stream().parallel()
        .forEach(element -> {
              var result = parseElementByTag(element.getHtmlTag(), element.getHtmlID());
              jsonObject.put(element.getHtmlID(), result);
            }
        );
    return jsonObject;
  }

}