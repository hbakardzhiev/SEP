package com.example.demo.modules;

import java.io.IOException;

public class ParserCR extends ParserBase {

  public ParserCR(ParserCN parserCN) throws IOException {
    setSheetType(SheetType.CR);
    passCN(parserCN);
  }

  private void passCN(ParserCN parserCN) throws IOException {
    this.setDocumentByUrl(parserCN.getDocument().values().stream().map(
        document -> document.select(String.format("a:matchesOwn(%s)", "^CR[\\d]{6},"))
            .attr("href")));

  }

}
