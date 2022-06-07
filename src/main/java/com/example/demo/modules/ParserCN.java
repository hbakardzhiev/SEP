package com.example.demo.modules;

import java.io.IOException;
import java.util.stream.Stream;

/** Parses CN type pages */
public class ParserCN extends ParserBase {

  /**
   * Creates the CN parser which extends ParserBase
   *
   * @param input String url of the page
   * @throws IOException
   */
  public ParserCN(String input) throws IOException {
    setSheetType(SheetType.CN);
    setDocumentByUrl(Stream.of(input));
  }
}
