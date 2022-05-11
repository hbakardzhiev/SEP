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

  public Parser() throws IOException {
    final var input = new File(getClass().getClassLoader().getResource("example.html").getFile());
    document = Jsoup.parse(input, "UTF-8");
  }

  private String parseElementByTag(String tag, String id) {
    return this.document.select(String.format("[%s=%s]", tag, id)).text();
  }

  public JSONObject parseElement() {
    var jsonObject = new JSONObject();
    cnRepository.findAll().stream().parallel()
        .forEach(element -> {
              var result = parseElementByTag(element.getHtmlTag(), element.getHtmlID());
              jsonObject.append(element.getHtmlID(), result);
            }
        );
    return jsonObject;
  }
}
