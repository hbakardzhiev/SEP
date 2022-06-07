package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.Util;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class ParserCNTest {

  /** Method under test: {@link ParserCN#ParserCN(String)} */
  @Test
  void testConstructor1() throws IOException {
    ParserCN actualParserCN = new ParserCN(Util.CHANGE_NOTICE_EXAMPLE_HTML);
    assertEquals(1, actualParserCN.getDocument().size());
    assertEquals(SheetType.CN, actualParserCN.getSheetType());
  }

  /** Method under test: {@link ParserCN#ParserCN(String)} */
  @Test
  void testConstructor2() throws IOException {
    ParserCN actualParserCN = new ParserCN(Util.CHANGE_NOTICE_EXAMPLE_HTML);
    assertEquals(1, actualParserCN.getDocument().size());
    assertEquals(SheetType.CN, actualParserCN.getSheetType());
  }
}
