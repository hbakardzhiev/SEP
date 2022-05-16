package com.example.demo.modules;

import java.io.IOException;
import java.util.stream.Stream;

public class ParserCT extends State {

  public ParserCT(ParserBase parser) {
    super(parser);
  }

  @Override
  public Stream<SheetSource> parsePage() throws IOException {
    this.parser.setDocument(this.parser.parseNextPage(SheetType.CT, "ECT[0-9]"));
    this.setSheetType(SheetType.CT);
    return super.parsePage();
  }
}
