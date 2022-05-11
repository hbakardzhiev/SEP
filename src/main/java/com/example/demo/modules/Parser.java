package com.example.demo.modules;

import com.example.demo.Util;
import com.example.demo.repository.CNRepository;
import com.example.demo.repository.ParserRepository;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

public class Parser implements ParserRepository {

  private final Document document;


  @Autowired
  private CNRepository cnRepository;


  public Parser(String startUrl) throws IOException {
    var inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream(startUrl);
    document = Jsoup.parse(Util.readFromInputStream(inputStream));
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

  /**
   * @Yasen would have to finish CR
   */
  public String parseCR() {
    var result = this.document.select("a:matchesOwn(CR[0-9])").text();
    return result;
  }
}