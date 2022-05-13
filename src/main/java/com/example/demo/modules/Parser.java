package com.example.demo.modules;

import com.example.demo.Util;
import com.example.demo.repository.ParserRepository;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

public class Parser implements ParserRepository {

  private final Document document;

  private SheetType sheetTypeEnum;

  @Autowired
  private ParserBase parserBase;

  public Parser(String startUrl, SheetType type) throws IOException {
    var inputStream = this.getClass().getClassLoader().getResourceAsStream(startUrl);
    document = Jsoup.parse(Util.readFromInputStream(inputStream));
    sheetTypeEnum = type;
  }

  private String parseElementByTag(String tag, String id) {
    return this.parserBase.document.select(String.format("[%s=%s]", tag, id)).text();
  }

  public JSONObject parseElements() throws IOException {
    var jsonObject = new JSONObject();
    var parser = new ParserCN(parserBase);
    parser.parser.changeState(new ParserCR(parserBase));


    parser.parser.parsePage().forEach(element -> {
      System.out.println(parseElementByTag(element.getHtmlTag(), element.getHtmlID()));
          var result = parseElementByTag(element.getHtmlTag(), element.getHtmlID());
          jsonObject.put(element.getHtmlID(), result);
        }
    );
    return jsonObject;
  }

  public JSONObject parseCR(SheetType type) throws IOException {
    final String link = this.document.select("a:matchesOwn(CR[0-9])").attr("href");
    final Parser parser = new Parser(link, type);
    return parser.parseElements();
  }
}
