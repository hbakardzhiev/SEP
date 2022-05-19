package com.example.demo.modules;

import java.io.IOException;
import java.util.stream.Stream;

public class ParserCN extends ParserBase {



  public ParserCN(String input) throws IOException {
    setSheetType(SheetType.CN);
    setDocumentByUrl(Stream.of(input));
  }



}
