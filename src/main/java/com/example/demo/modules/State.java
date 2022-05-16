package com.example.demo.modules;

import java.io.IOException;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

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
        .filter(element -> element.getSheetSourceType().equals(this.getSheetType())).parallel();
    return stream;
  }

    public String parseElementByTag(String tag, String id) {
    return this.parser.document.select(String.format("[%s=%s]", tag, id)).text();
  }


}
