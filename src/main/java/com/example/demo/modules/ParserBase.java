package com.example.demo.modules;

import com.example.demo.Util;
import com.example.demo.repository.SheetSourceRepository;
import java.io.IOException;
import java.util.stream.Stream;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

public class ParserBase implements IParserBase {

  private State state;

  protected Document document;

  private String url;

  @Autowired
  protected SheetSourceRepository sheetSourceRepository;

  public ParserBase() throws IOException {
    this.url = "";
    this.state = new ParserCN(this);
  }

  public void setDocument(String url) throws IOException {
    this.url = url;
    var inputStream = this.getClass().getClassLoader().getResourceAsStream(this.url);
    document = Jsoup.parse(Util.readFromInputStream(inputStream));
  }

  public void changeState(State state) {
    this.state = state;
  }

  public Stream<SheetSource> parsePage() throws IOException {
    return state.parsePage();
  }

  public String parseNextPage(SheetType type, String pattern) throws IOException {
    final String link = this.document.select(String.format("a:matchesOwn(%s)", pattern)).attr("href");
    return link;
  }

}
