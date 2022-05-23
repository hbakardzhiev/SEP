package com.example.demo.services;

import com.example.demo.Util;
import com.example.demo.modules.ParserCN;
import com.example.demo.modules.ParserCR;
import com.example.demo.modules.ParserCT;
import com.example.demo.modules.ParserDMR;
import com.example.demo.repository.SheetSourceRepository;
import java.io.IOException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** The Service which calls the repository and parses the correct page. */
@Service
public class ParserService {

  @Autowired protected SheetSourceRepository sheetSourceRepository;

  /**
   * Calls the ParserCN object and gives a static url
   *
   * @return parsed CN page
   * @throws IOException
   */
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCN()
      throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cnParser =
        new ParserCN(Util.CHANGE_NOTICE_EXAMPLE_HTML)
            .parsePage(sheetSourceStream)
            .collect(Collectors.toList());
    return cnParser;
  }

  /**
   * Calls the parserCR object and returns the parsed CR page
   *
   * @return parsed CR page
   * @throws IOException
   */
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCR()
      throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cnParser = new ParserCN(Util.CHANGE_NOTICE_EXAMPLE_HTML);
    final var crParser = new ParserCR(cnParser);
    return crParser.parsePage(sheetSourceStream).collect(Collectors.toList());
  }

  /**
   * Calls the parserCT object and returns the parsed CT page
   *
   * @return parsed CT page
   * @throws IOException
   */
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCT()
      throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cnParser = new ParserCN(Util.CHANGE_NOTICE_EXAMPLE_HTML);
    final var ctParser = new ParserCT(cnParser);
    return ctParser.parsePage(sheetSourceStream).collect(Collectors.toList());
  }

  /**
   * Calls the parserDMR object and returns the parsed DMR page
   *
   * @return parsed DMR page
   * @throws IOException
   */
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseDMR()
      throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cnParser = new ParserCN(Util.CHANGE_NOTICE_EXAMPLE_HTML);
    final var ctParser = new ParserCT(cnParser);
    final var dmrParser = new ParserDMR(ctParser.getDocument().values().stream().parallel());
    return dmrParser.parsePage(sheetSourceStream).collect(Collectors.toList());
  }
}
