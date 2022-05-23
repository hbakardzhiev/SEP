package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;



import org.junit.jupiter.api.Test;

class ParserCRTest {
  /** Method under test: {@link ParserCR#ParserCR(ParserCN)} */
  @Test
  void testConstructor1() throws IOException {
    ParserCR actualParserCR = new ParserCR(new ParserCN(""));
    assertEquals(1, actualParserCR.getDocument().size());
    assertEquals(SheetType.CR, actualParserCR.getSheetType());
  }

  /** Method under test: {@link ParserCR#ParserCR(ParserCN)} */
  @Test
  void testConstructor2() throws IOException {
    ParserCN parserCN = new ParserCN("");
    parserCN.setDocument(new HashMap<>());
    ParserCR actualParserCR = new ParserCR(parserCN);
    assertTrue(actualParserCR.getDocument().isEmpty());
    assertEquals(SheetType.CR, actualParserCR.getSheetType());
  }
}
