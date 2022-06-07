package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class ParserDMRTest {
  /** Method under test: {@link ParserDMR#ParserDMR(ParserCT)} */
  @Test
  void testConstructor() throws IOException {
    ParserDMR actualParserDMR = new ParserDMR(new ParserCT(new ParserCN("")));
    assertTrue(actualParserDMR.getDocument().isEmpty());
    assertEquals(SheetType.DMR, actualParserDMR.getSheetType());
  }

  /** Method under test: {@link ParserDMR#ParserDMR(ParserCT)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testConstructor2() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "com.example.demo.modules.ParserCT.getDocument()" because "parserCT" is null
    //       at com.example.demo.modules.ParserDMR.passCT(ParserDMR.java:28)
    //       at com.example.demo.modules.ParserDMR.<init>(ParserDMR.java:18)
    //   In order to prevent <init>(ParserCT)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   <init>(ParserCT).
    //   See https://diff.blue/R013 to resolve this issue.

    new ParserDMR(null);
  }
}
