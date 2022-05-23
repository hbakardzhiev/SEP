package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class SheetSourceTest {
    /**
     * Method under test: {@link SheetSource#SheetSource(String, String, SheetType)}
     */
    @Test
    void testConstructor1() {
        SheetSource actualSheetSource = new SheetSource("Html ID", "Type", SheetType.CN);

        assertEquals("Html ID", actualSheetSource.getHtmlID());
        assertEquals("Type", actualSheetSource.getDataType());
        assertEquals(SheetType.CN, actualSheetSource.getSheetSourceType());
        assertNull(actualSheetSource.getName());
        assertNull(actualSheetSource.getId());
        assertEquals("attrid", actualSheetSource.getHtmlTag());
    }

    /**
     * Method under test: {@link SheetSource#SheetSource(String, String, String, SheetType)}
     */
    @Test
    void testConstructor2() {
        SheetSource actualSheetSource = new SheetSource("Html Tag", "Html ID", "Type", SheetType.CN);

        assertEquals("Html ID", actualSheetSource.getHtmlID());
        assertEquals("Type", actualSheetSource.getDataType());
        assertEquals(SheetType.CN, actualSheetSource.getSheetSourceType());
        assertNull(actualSheetSource.getName());
        assertNull(actualSheetSource.getId());
        assertEquals("Html Tag", actualSheetSource.getHtmlTag());
    }

    /**
     * Method under test: {@link SheetSource#equals(Object)}
     */
    @Test
    void testEquals1() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(123L);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");
        assertNotEquals(sheetSource, null);
    }

    /**
     * Method under test: {@link SheetSource#equals(Object)}
     */
    @Test
    void testEquals2() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(123L);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");
        assertNotEquals(sheetSource, "Different type to SheetSource");
    }

    /**
     * Method under test: {@link SheetSource#equals(Object)}
     */
    @Test
    void testEquals3() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(123L);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");
        assertEquals(sheetSource, sheetSource);
        int expectedHashCodeResult = sheetSource.hashCode();
        assertEquals(expectedHashCodeResult, sheetSource.hashCode());
    }

    /**
     * Method under test: {@link SheetSource#equals(Object)}
     */
    @Test
    void testEquals4() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(123L);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");

        SheetSource sheetSource1 = new SheetSource();
        sheetSource1.setHtmlID("Html ID");
        sheetSource1.setHtmlTag("Html Tag");
        sheetSource1.setId(123L);
        sheetSource1.setName("Name");
        sheetSource1.setSheetSourceType(SheetType.CN);
        sheetSource1.setDataType("Type");
        assertEquals(sheetSource, sheetSource1);
        int expectedHashCodeResult = sheetSource.hashCode();
        assertEquals(expectedHashCodeResult, sheetSource1.hashCode());
    }

    /**
     * Method under test: {@link SheetSource#equals(Object)}
     */
    @Test
    void testEquals5() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(1L);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");

        SheetSource sheetSource1 = new SheetSource();
        sheetSource1.setHtmlID("Html ID");
        sheetSource1.setHtmlTag("Html Tag");
        sheetSource1.setId(123L);
        sheetSource1.setName("Name");
        sheetSource1.setSheetSourceType(SheetType.CN);
        sheetSource1.setDataType("Type");
        assertNotEquals(sheetSource, sheetSource1);
    }

    /**
     * Method under test: {@link SheetSource#equals(Object)}
     */
    @Test
    void testEquals6() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(null);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");

        SheetSource sheetSource1 = new SheetSource();
        sheetSource1.setHtmlID("Html ID");
        sheetSource1.setHtmlTag("Html Tag");
        sheetSource1.setId(123L);
        sheetSource1.setName("Name");
        sheetSource1.setSheetSourceType(SheetType.CN);
        sheetSource1.setDataType("Type");
        assertNotEquals(sheetSource, sheetSource1);
    }

    /**
     * Method under test: {@link SheetSource#equals(Object)}
     */
    @Test
    void testEquals7() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(123L);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");
        assertNotEquals(sheetSource, null);
    }

    /**
     * Method under test: {@link SheetSource#equals(Object)}
     */
    @Test
    void testEquals8() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(123L);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");
        assertNotEquals(sheetSource, "Different type to SheetSource");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SheetSource#equals(Object)}
     *   <li>{@link SheetSource#hashCode()}
     * </ul>
     */
    @Test
    void testEquals9() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(123L);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");
        assertEquals(sheetSource, sheetSource);
        int expectedHashCodeResult = sheetSource.hashCode();
        assertEquals(expectedHashCodeResult, sheetSource.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SheetSource#equals(Object)}
     *   <li>{@link SheetSource#hashCode()}
     * </ul>
     */
    @Test
    void testEquals10() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(123L);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");

        SheetSource sheetSource1 = new SheetSource();
        sheetSource1.setHtmlID("Html ID");
        sheetSource1.setHtmlTag("Html Tag");
        sheetSource1.setId(123L);
        sheetSource1.setName("Name");
        sheetSource1.setSheetSourceType(SheetType.CN);
        sheetSource1.setDataType("Type");
        assertEquals(sheetSource, sheetSource1);
        int expectedHashCodeResult = sheetSource.hashCode();
        assertEquals(expectedHashCodeResult, sheetSource1.hashCode());
    }

    /**
     * Method under test: {@link SheetSource#equals(Object)}
     */
    @Test
    void testEquals11() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(1L);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");

        SheetSource sheetSource1 = new SheetSource();
        sheetSource1.setHtmlID("Html ID");
        sheetSource1.setHtmlTag("Html Tag");
        sheetSource1.setId(123L);
        sheetSource1.setName("Name");
        sheetSource1.setSheetSourceType(SheetType.CN);
        sheetSource1.setDataType("Type");
        assertNotEquals(sheetSource, sheetSource1);
    }

    /**
     * Method under test: {@link SheetSource#equals(Object)}
     */
    @Test
    void testEquals12() {
        SheetSource sheetSource = new SheetSource();
        sheetSource.setHtmlID("Html ID");
        sheetSource.setHtmlTag("Html Tag");
        sheetSource.setId(null);
        sheetSource.setName("Name");
        sheetSource.setSheetSourceType(SheetType.CN);
        sheetSource.setDataType("Type");

        SheetSource sheetSource1 = new SheetSource();
        sheetSource1.setHtmlID("Html ID");
        sheetSource1.setHtmlTag("Html Tag");
        sheetSource1.setId(123L);
        sheetSource1.setName("Name");
        sheetSource1.setSheetSourceType(SheetType.CN);
        sheetSource1.setDataType("Type");
        assertNotEquals(sheetSource, sheetSource1);
    }
}

