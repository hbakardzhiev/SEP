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

  private JSONObject extracted(State parser) throws IOException {
    var jsonObject = new JSONObject();
    parser.parsePage().forEach(element -> {
          var result = parseElementByTag(element.getHtmlTag(), element.getHtmlID());
          jsonObject.put(element.getHtmlID(), result);
        }
    );
    return jsonObject;
  }

  @Override
  public JSONObject parseCN() throws IOException {
    var parser = new ParserCN(parserBase);
    parserBase.changeState(new ParserCN(parserBase));
    parser.parser.changeState(new ParserCN(parserBase));
    return extracted(parser);
  }

  @Override
  public JSONObject parseCR() throws IOException {
    var parser = new ParserCR(parserBase);
    parserBase.changeState(new ParserCR(parserBase));
    parser.parser.changeState(new ParserCR(parserBase));
    return extracted(parser);
  }

  @Override
  public JSONObject parseCT() throws IOException {
    parserBase.changeState(new ParserCT(parserBase));
    var parser = new ParserCT(parserBase);
//    parserBase.changeState(new ParserCT(parserBase));
    parser.parser.changeState(new ParserCT(parserBase));
    return extracted(parser);
  }


}
