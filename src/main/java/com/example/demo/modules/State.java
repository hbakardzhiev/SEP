package com.example.demo.modules;

import java.io.IOException;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

public abstract class State {

  @Getter
  @Setter
  private SheetType sheetType;
  protected ParserBase parser;

  public State(ParserBase parser) {
    this.parser = parser;
  }


  public Stream<SheetSource> parsePage() throws IOException {
    var stream = this.parser.sheetSourceRepository.findAll().stream()
        //element is a row in table sheetsource which is connected to sheetsource_sheetsource_type
        .filter(element -> element.getSheetSourceType().contains(this.getSheetType())).parallel();
    return stream;
  }

  public String parseNextPage(String pattern) throws IOException {
    return this.parser.document.select(String.format("a:matchesOwn(%s)", pattern)).attr("href");
  }

}
