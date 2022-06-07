package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ParserCNTest {
    /**
     * Method under test: {@link ParserCN#ParserCN(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.io.Reader.<init>(Reader.java:168)
        //       at java.io.InputStreamReader.<init>(InputStreamReader.java:76)
        //       at com.example.demo.Util.readFromInputStream(Util.java:25)
        //       at com.example.demo.modules.ParserBase.lambda$setDocumentByUrl$0(ParserBase.java:46)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.stream.Streams$StreamBuilderImpl.forEachRemaining(Streams.java:411)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.example.demo.modules.ParserBase.setDocumentByUrl(ParserBase.java:53)
        //       at com.example.demo.modules.ParserCN.<init>(ParserCN.java:17)
        //   In order to prevent <init>(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   <init>(String).
        //   See https://diff.blue/R013 to resolve this issue.

        new ParserCN("Input");
    }

    /**
     * Method under test: {@link ParserCN#ParserCN(String)}
     */
    @Test
    void testConstructor2() throws IOException {
        ParserCN actualParserCN = new ParserCN("");
        assertEquals(1, actualParserCN.getDocument().size());
        assertEquals(SheetType.CN, actualParserCN.getSheetType());
    }
}

