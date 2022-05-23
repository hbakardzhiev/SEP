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
    void testConstructor1() throws IOException {
        ParserCN actualParserCN = new ParserCN("");
        assertEquals(1, actualParserCN.getDocument().size());
        assertEquals(SheetType.CN, actualParserCN.getSheetType());
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

