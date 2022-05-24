package com.example.demo.modules;

import java.io.IOException;

/** Creates parses that parses CR pages */
public class ParserCR extends ParserBase {

  /**
   * Takes the CN parses and parses the CR pages
   *
   * @param parserCN the parses that parses CN
   * @throws IOException
   */
  public ParserCR(ParserCN parserCN) throws IOException {
    setSheetType(SheetType.CR);
    passCN(parserCN);
  }

  /**
   * Goes through the CN web page takes the CR links and sets the urls in CR.
   *
   * @param parserCN the parses that parses CN
   * @throws IOException
   */
  private void passCN(ParserCN parserCN) throws IOException {
    this.setDocumentByUrl(
        parserCN.getDocument().values().stream()
            .map(
                document ->
                    document
                        .select(String.format("a:matchesOwn(%s)", "^CR[\\d]{6},"))
                        .attr("href")));
  }
}
