package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;



import org.junit.jupiter.api.Test;

class ParserCTTest {
  /** Method under test: {@link ParserCT#ParserCT(ParserCN)} */
  @Test
  void testConstructor1() throws IOException {
    ParserCT actualParserCT = new ParserCT(new ParserCN(""));
    assertTrue(actualParserCT.getDocument().isEmpty());
    assertEquals(SheetType.CT, actualParserCT.getSheetType());
  }

  /** Method under test: {@link ParserCT#ParserCT(ParserCN)} */
  @Test
  void testConstructor2() throws IOException {
    ParserCN parserCN = new ParserCN("");
    parserCN.setDocument(new HashMap<>());
    ParserCT actualParserCT = new ParserCT(parserCN);
    assertTrue(actualParserCT.getDocument().isEmpty());
    assertEquals(SheetType.CT, actualParserCT.getSheetType());
  }
}
