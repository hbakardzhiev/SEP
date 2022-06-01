package com.example.demo.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Autowired
    private ParserService parserService;

    @MockBean
    private SheetSourceRepository sheetSourceRepository;

    /**
     * Method under test: {@link ParserService#parseCN()}
     */
    @Test
    void testParseCN() throws IOException {
        when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
        this.parserService.parseCN();
        verify(this.sheetSourceRepository).findAll();
    }

    /**
     * Method under test: {@link ParserService#parseCR()}
     */
    @Test
    void testParseCR() throws IOException {
        when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
        this.parserService.parseCR();
        verify(this.sheetSourceRepository).findAll();
    }

    /**
     * Method under test: {@link ParserService#parseCT()}
     */
    @Test
    void testParseCT() throws IOException {
        when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
        this.parserService.parseCT();
        verify(this.sheetSourceRepository).findAll();
    }

    /**
     * Method under test: {@link ParserService#parseDMR()}
     */
    @Test
    void testParseDMR() throws IOException {
        when(this.sheetSourceRepository.findAll()).thenReturn(new ArrayList<>());
        this.parserService.parseDMR();
        verify(this.sheetSourceRepository).findAll();
    }
}

