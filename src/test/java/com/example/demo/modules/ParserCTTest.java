package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.nodes.Document;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class ParserCTTest {
  /** Method under test: {@link ParserCT#ParserCT(ParserCN)} */
  @Test
  void testConstructor() throws IOException {
    ParserCT actualParserCT = new ParserCT(new ParserCN(""));
    assertTrue(actualParserCT.getDocument().isEmpty());
    assertEquals(SheetType.CT, actualParserCT.getSheetType());
  }

  /** Method under test: {@link ParserCT#ParserCT(ParserCN)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testConstructor2() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "com.example.demo.modules.ParserCN.getDocument()" because "parserCN" is null
    //       at com.example.demo.modules.ParserCT.passCN(ParserCT.java:30)
    //       at com.example.demo.modules.ParserCT.<init>(ParserCT.java:19)
    //   In order to prevent <init>(ParserCN)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   <init>(ParserCN).
    //   See https://diff.blue/R013 to resolve this issue.

    new ParserCT(null);
  }

  /** Method under test: {@link ParserCT#ParserCT(ParserCN)} */
  @Test
  void testConstructor3() throws IOException {
    ParserCN parserCN = new ParserCN("");
    parserCN.setDocument(new HashMap<>());
    ParserCT actualParserCT = new ParserCT(parserCN);
    assertTrue(actualParserCT.getDocument().isEmpty());
    assertEquals(SheetType.CT, actualParserCT.getSheetType());
  }

  /** Method under test: {@link ParserCT#ParserCT(ParserCN)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testConstructor4() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.jsoup.nodes.Document.select(String)"
    // because "element" is null
    //       at com.example.demo.modules.ParserCT.lambda$passCN$0(ParserCT.java:33)
    //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
    //       at java.util.HashMap$ValueSpliterator.forEachRemaining(HashMap.java:1779)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ReduceOps$ReduceTask.doLeaf(ReduceOps.java:960)
    //       at java.util.stream.ReduceOps$ReduceTask.doLeaf(ReduceOps.java:934)
    //       at java.util.stream.AbstractTask.compute(AbstractTask.java:327)
    //       at java.util.concurrent.CountedCompleter.exec(CountedCompleter.java:754)
    //       at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:373)
    //       at java.util.concurrent.ForkJoinTask.invoke(ForkJoinTask.java:686)
    //       at java.util.stream.ReduceOps$ReduceOp.evaluateParallel(ReduceOps.java:927)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:233)
    //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
    //       at com.example.demo.modules.ParserCT.passCN(ParserCT.java:39)
    //       at com.example.demo.modules.ParserCT.<init>(ParserCT.java:19)
    //   In order to prevent <init>(ParserCN)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   <init>(ParserCN).
    //   See https://diff.blue/R013 to resolve this issue.

    HashMap<String, Document> stringDocumentMap = new HashMap<>();
    stringDocumentMap.put("Key", null);

    ParserCN parserCN = new ParserCN("");
    parserCN.setDocument(stringDocumentMap);
    new ParserCT(parserCN);
  }

  /** Method under test: {@link ParserCT#ParserCT(ParserCN)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testConstructor5() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.jsoup.nodes.Document.select(String)"
    // because "element" is null
    //       at com.example.demo.modules.ParserCT.lambda$passCN$0(ParserCT.java:33)
    //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
    //       at java.util.HashMap$ValueSpliterator.forEachRemaining(HashMap.java:1779)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ReduceOps$ReduceTask.doLeaf(ReduceOps.java:960)
    //       at java.util.stream.ReduceOps$ReduceTask.doLeaf(ReduceOps.java:934)
    //       at java.util.stream.AbstractTask.compute(AbstractTask.java:327)
    //       at java.util.concurrent.CountedCompleter.exec(CountedCompleter.java:754)
    //       at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:373)
    //       at java.util.concurrent.ForkJoinTask.invoke(ForkJoinTask.java:686)
    //       at java.util.stream.ReduceOps$ReduceOp.evaluateParallel(ReduceOps.java:927)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:233)
    //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
    //       at com.example.demo.modules.ParserCT.passCN(ParserCT.java:39)
    //       at com.example.demo.modules.ParserCT.<init>(ParserCT.java:19)
    //   In order to prevent <init>(ParserCN)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   <init>(ParserCN).
    //   See https://diff.blue/R013 to resolve this issue.

    HashMap<String, Document> stringDocumentMap = new HashMap<>();
    stringDocumentMap.put(
        "a:matchesOwn(^([M]?[D]?[E]?CT[\\d]{6}))",
        Document.createShell("a:matchesOwn(^([M]?[D]?[E]?CT[\\d]{6}))"));
    stringDocumentMap.put("Key", null);

    ParserCN parserCN = new ParserCN("");
    parserCN.setDocument(stringDocumentMap);
    new ParserCT(parserCN);
  }
}
