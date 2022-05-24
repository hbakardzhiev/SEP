package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.modules.SheetSource;
import com.example.demo.modules.SheetType;
import com.example.demo.repository.SheetSourceRepository;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ParserService.class})
@ExtendWith(SpringExtension.class)
class ParserServiceTest {
  @Autowired private ParserService parserService;

  @MockBean private SheetSourceRepository sheetSourceRepository;

  /** Method under test: {@link ParserService#parseCN()} */
  @Test
  void testParseCN() throws IOException {
    when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
    assertTrue(this.parserService.parseCN().isEmpty());
    verify(this.sheetSourceRepository).findAll();
  }

  /** Method under test: {@link ParserService#parseCN()} */
  @Test
  void testParseCN2() throws IOException {
    SheetSource sheetSource = new SheetSource();
    sheetSource.setHtmlID("UU:");
    sheetSource.setHtmlTag("UU:");
    sheetSource.setId(123L);
    sheetSource.setName("UU:");
    sheetSource.setSheetSourceType(SheetType.CN);
    sheetSource.setDataType("UU:");

    ArrayList<SheetSource> sheetSourceList = new ArrayList<>();
    sheetSourceList.add(sheetSource);
    when(this.sheetSourceRepository.findAll()).thenReturn(sheetSourceList);
    assertEquals(1, this.parserService.parseCN().size());
    verify(this.sheetSourceRepository).findAll();
  }

  /** Method under test: {@link ParserService#parseCN()} */
  @Test
  void testParseCN3() throws IOException {
    SheetSource sheetSource = new SheetSource();
    sheetSource.setHtmlID("UU:");
    sheetSource.setHtmlTag("UU:");
    sheetSource.setId(123L);
    sheetSource.setName("UU:");
    sheetSource.setSheetSourceType(SheetType.CN);
    sheetSource.setDataType("UU:");

    SheetSource sheetSource1 = new SheetSource();
    sheetSource1.setHtmlID("UU:");
    sheetSource1.setHtmlTag("UU:");
    sheetSource1.setId(123L);
    sheetSource1.setName("UU:");
    sheetSource1.setSheetSourceType(SheetType.CN);
    sheetSource1.setDataType("UU:");

    ArrayList<SheetSource> sheetSourceList = new ArrayList<>();
    sheetSourceList.add(sheetSource1);
    sheetSourceList.add(sheetSource);
    when(this.sheetSourceRepository.findAll()).thenReturn(sheetSourceList);
    assertEquals(2, this.parserService.parseCN().size());
    verify(this.sheetSourceRepository).findAll();
  }

  /** Method under test: {@link ParserService#parseCR()} */
  @Test
  void testParseCR() throws IOException {
    when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
    assertTrue(this.parserService.parseCR().isEmpty());
    verify(this.sheetSourceRepository).findAll();
  }

  /** Method under test: {@link ParserService#parseCR()} */
  @Test
  void testParseCR2() throws IOException {
    when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
    assertTrue(this.parserService.parseCR().isEmpty());
    verify(this.sheetSourceRepository).findAll();
  }

  /** Method under test: {@link ParserService#parseCT()} */
  @Test
  void testParseCT() throws IOException {
    when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
    assertTrue(this.parserService.parseCT().isEmpty());
    verify(this.sheetSourceRepository).findAll();
  }
}
