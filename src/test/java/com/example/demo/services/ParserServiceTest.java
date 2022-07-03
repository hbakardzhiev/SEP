package com.example.demo.services;

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

  @Test
  void testParseEverything() throws Exception {
    when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
    this.parserService.parseEverything("CN000001");
    verify(this.sheetSourceRepository).findAll();
  }

  @Test
  void testParseEverything1() throws Exception {
    SheetSource sheetSource = new SheetSource();
    sheetSource.setDataType("ExternalPage.html");
    sheetSource.setHtmlID("ExternalPage.html");
    sheetSource.setHtmlTag("ExternalPage.html");
    sheetSource.setId(123L);
    sheetSource.setName("ExternalPage.html");
    sheetSource.setSheetSourceType(SheetType.CN);

    ArrayList<SheetSource> sheetSourceList = new ArrayList<>();
    sheetSourceList.add(sheetSource);
    when(this.sheetSourceRepository.findAll()).thenReturn(sheetSourceList);
    this.parserService.parseEverything("CN000001");
    verify(this.sheetSourceRepository).findAll();
  }

  @Test
  void testParseEverything2() throws Exception {
    when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
    this.parserService.parseEverything("CN000001");
    verify(this.sheetSourceRepository).findAll();
  }

  @Test
  void testParseEverything5() throws Exception {
    when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
    this.parserService.parseEverything("CN000001");
    verify(this.sheetSourceRepository).findAll();
  }

  /** Method under test: {@link ParserService#parseEverything(String)} */
  @Test
  void testParseEverything6() throws Exception {
    SheetSource sheetSource = new SheetSource();
    sheetSource.setDataType("ExternalPage.html");
    sheetSource.setHtmlID("ExternalPage.html");
    sheetSource.setHtmlTag("ExternalPage.html");
    sheetSource.setId(123L);
    sheetSource.setName("ExternalPage.html");
    sheetSource.setSheetSourceType(SheetType.CN);

    ArrayList<SheetSource> sheetSourceList = new ArrayList<>();
    sheetSourceList.add(sheetSource);
    when(this.sheetSourceRepository.findAll()).thenReturn(sheetSourceList);
    this.parserService.parseEverything("CN000001");
    verify(this.sheetSourceRepository).findAll();
  }

  @Test
  void testParseEverything7() throws Exception {
    when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
    this.parserService.parseEverything("CN000001");
    verify(this.sheetSourceRepository).findAll();
  }
}
