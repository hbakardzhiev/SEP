package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.nodes.Document;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class ParserCRTest {
  /** Method under test: {@link ParserCR#ParserCR(ParserCN)} */
  @Test
  void testConstructor() throws IOException {
    ParserCR actualParserCR = new ParserCR(new ParserCN(""));
    assertEquals(1, actualParserCR.getDocument().size());
    assertEquals(SheetType.CR, actualParserCR.getSheetType());
  }

  /** Method under test: {@link ParserCR#ParserCR(ParserCN)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testConstructor2() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "com.example.demo.modules.ParserCN.getDocument()" because "parserCN" is null
    //       at com.example.demo.modules.ParserCR.passCN(ParserCR.java:27)
    //       at com.example.demo.modules.ParserCR.<init>(ParserCR.java:16)
    //   In order to prevent <init>(ParserCN)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   <init>(ParserCN).
    //   See https://diff.blue/R013 to resolve this issue.

    new ParserCR(null);
  }

  /** Method under test: {@link ParserCR#ParserCR(ParserCN)} */
  @Test
  void testConstructor3() throws IOException {
    ParserCN parserCN = new ParserCN("");
    parserCN.setDocument(new HashMap<>());
    ParserCR actualParserCR = new ParserCR(parserCN);
    assertTrue(actualParserCR.getDocument().isEmpty());
    assertEquals(SheetType.CR, actualParserCR.getSheetType());
  }

  /** Method under test: {@link ParserCR#ParserCR(ParserCN)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testConstructor4() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.jsoup.nodes.Document.select(String)"
    // because "document" is null
    //       at com.example.demo.modules.ParserCR.lambda$passCN$0(ParserCR.java:31)
    //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
    //       at java.util.HashMap$ValueSpliterator.forEachRemaining(HashMap.java:1779)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
    //       at com.example.demo.modules.ParserBase.setDocumentByUrl(ParserBase.java:53)
    //       at com.example.demo.modules.ParserCR.passCN(ParserCR.java:26)
    //       at com.example.demo.modules.ParserCR.<init>(ParserCR.java:16)
    //   In order to prevent <init>(ParserCN)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   <init>(ParserCN).
    //   See https://diff.blue/R013 to resolve this issue.

    HashMap<String, Document> stringDocumentMap = new HashMap<>();
    stringDocumentMap.put("Key", null);

    ParserCN parserCN = new ParserCN("");
    parserCN.setDocument(stringDocumentMap);
    new ParserCR(parserCN);
  }

  /** Method under test: {@link ParserCR#ParserCR(ParserCN)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testConstructor5() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.jsoup.nodes.Document.select(String)"
    // because "document" is null
    //       at com.example.demo.modules.ParserCR.lambda$passCN$0(ParserCR.java:31)
    //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
    //       at java.util.HashMap$ValueSpliterator.forEachRemaining(HashMap.java:1779)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
    //       at com.example.demo.modules.ParserBase.setDocumentByUrl(ParserBase.java:53)
    //       at com.example.demo.modules.ParserCR.passCN(ParserCR.java:26)
    //       at com.example.demo.modules.ParserCR.<init>(ParserCR.java:16)
    //   In order to prevent <init>(ParserCN)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   <init>(ParserCN).
    //   See https://diff.blue/R013 to resolve this issue.

    HashMap<String, Document> stringDocumentMap = new HashMap<>();
    stringDocumentMap.put("a:matchesOwn(%s)", Document.createShell("a:matchesOwn(%s)"));
    stringDocumentMap.put("Key", null);

    ParserCN parserCN = new ParserCN("");
    parserCN.setDocument(stringDocumentMap);
    new ParserCR(parserCN);
  }

  /** Method under test: {@link ParserCR#ParserCR(ParserCN)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testConstructor6() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.jsoup.nodes.Document.select(String)"
    // because "document" is null
    //       at com.example.demo.modules.ParserCR.lambda$passCN$0(ParserCR.java:31)
    //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
    //       at java.util.HashMap$ValueSpliterator.forEachRemaining(HashMap.java:1779)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
    //       at com.example.demo.modules.ParserBase.setDocumentByUrl(ParserBase.java:53)
    //       at com.example.demo.modules.ParserCR.passCN(ParserCR.java:26)
    //       at com.example.demo.modules.ParserCR.<init>(ParserCR.java:16)
    //   In order to prevent <init>(ParserCN)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   <init>(ParserCN).
    //   See https://diff.blue/R013 to resolve this issue.

    HashMap<String, Document> stringDocumentMap = new HashMap<>();
    stringDocumentMap.put("CR999999,", Document.createShell("CR999999,"));
    stringDocumentMap.put("a:matchesOwn(%s)", Document.createShell("a:matchesOwn(%s)"));
    stringDocumentMap.put("Key", null);

    ParserCN parserCN = new ParserCN("");
    parserCN.setDocument(stringDocumentMap);
    new ParserCR(parserCN);
  }
}
