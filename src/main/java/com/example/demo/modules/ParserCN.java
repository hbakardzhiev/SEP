package com.example.demo.modules;

import java.io.IOException;

public class ParserCN extends State {

  public ParserCN(ParserBase parser) throws IOException {
    super(parser);
    this.parser.setDocument("Change Notice - Example.html");
    this.setSheetType(SheetType.CN);
  }

}
