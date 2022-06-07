package com.example.demo.modules;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ParserBaseTest {
  /** Method under test: {@link ParserBase#setDocumentByUrl(java.util.stream.Stream)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testSetDocumentByUrl() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "java.util.stream.Stream.map(java.util.function.Function)" because "url" is null
    //       at com.example.demo.modules.ParserBase.setDocumentByUrl(ParserBase.java:39)
    //   In order to prevent setDocumentByUrl(Stream)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   setDocumentByUrl(Stream).
    //   See https://diff.blue/R013 to resolve this issue.

    (new ParserCN("")).setDocumentByUrl(null);
  }

  /** Method under test: {@link ParserBase#parsePage(Stream)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testParsePage() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.util.stream.Stream.parallel()" because
    // "sheetSourceStream" is null
    //       at com.example.demo.modules.ParserBase.parsePage(ParserBase.java:92)
    //   In order to prevent parsePage(Stream)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   parsePage(Stream).
    //   See https://diff.blue/R013 to resolve this issue.

    (new ParserCN("")).parsePage(null);
  }
}
