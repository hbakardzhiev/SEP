package com.example.demo.modules;

import com.example.demo.Util;
import java.io.IOException;
import java.util.stream.Stream;
import org.jsoup.Jsoup;

public interface IParserBase {

  void setDocument(String url) throws IOException;

  public void changeState(State state);

  public Stream<SheetSource> parsePage() throws IOException;
}
