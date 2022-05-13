package com.example.demo.modules;

import java.io.IOException;
import java.util.stream.Stream;

public class ParserCR extends State{

  public ParserCR(ParserBase parser) {
    super(parser);
    this.setSheetType(SheetType.CR);
  }

  @Override
  public Stream<SheetSource> parsePage() throws IOException {
    var parser = new ParserCN(this.parser);
    this.parser.setDocument(parser.parseNextPage("CR[0-9]"));
    this.setSheetType(SheetType.CR);
    return super.parsePage();
  }

}
