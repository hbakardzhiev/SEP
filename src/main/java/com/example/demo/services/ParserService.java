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
import java.util.stream.Stream;

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
  public Stream<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCN()
      throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cnParser =
        new ParserCN(Util.CHANGE_NOTICE_EXAMPLE_HTML)
            .parsePage(sheetSourceStream);
    return cnParser;
  }

  /**
   * Calls the parserCR object and returns the parsed CR page
   *
   * @return parsed CR page
   * @throws IOException
   */
  public Stream<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCR()
      throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cnParser = new ParserCN(Util.CHANGE_NOTICE_EXAMPLE_HTML);
    final var crParser = new ParserCR(cnParser);
    return crParser.parsePage(sheetSourceStream);
  }

  /**
   * Calls the parserCT object and returns the parsed CT page
   *
   * @return parsed CT page
   * @throws IOException
   */
  public Stream<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCT()
      throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cnParser = new ParserCN(Util.CHANGE_NOTICE_EXAMPLE_HTML);
    final var ctParser = new ParserCT(cnParser);
    return ctParser.parsePage(sheetSourceStream);
  }

  /**
   * Calls the parserDMR object and returns the parsed DMR page
   *
   * @return parsed DMR page
   * @throws IOException
   */
  public Stream<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseDMR()
      throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll().stream();
    final var cnParser = new ParserCN(Util.CHANGE_NOTICE_EXAMPLE_HTML);
    final var ctParser = new ParserCT(cnParser);
    final var dmrParser = new ParserDMR(ctParser.getDocument().values().stream().parallel());
    return dmrParser.parsePage(sheetSourceStream);
  }

  /**
   * Combines all parsed pages in one list. TODO: Maybe make the methods above return streams
   * TODO: and combine the streams and also make the methods private
   * @return List of the parsed Cn, CR, Cts, DMRs pages
   * @throws IOException
   */

  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseEverything()
          throws IOException {
    final var cn = this.parseCN();
    final var cts = this.parseCT();
    final var cr = this.parseCR();
    final var dmrs = this.parseDMR();

    return Stream.of(cn, cr, cts, dmrs)
            .flatMap(x -> x)
            .collect(Collectors.toList());
  }

}
