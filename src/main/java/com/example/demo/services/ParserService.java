package com.example.demo.services;

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
   * Combines all parsed pages in one list. and combine the streams and also make the methods
   * private
   *
   * @return List of the parsed Cn, CR, Cts, DMRs pages
   * @throws IOException
   */
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseEverything(
      String input) throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll();
    final var parserCN = new ParserCN(input);
    final var parserCT = new ParserCT(parserCN);
    final var parserCR = new ParserCR(parserCN);
    final var parserDMR = new ParserDMR(parserCT);

    final var parsedCN = parserCN.parsePage(sheetSourceStream.stream());
    final var parsedCT = parserCT.parsePage(sheetSourceStream.stream());
    final var parsedCR = parserCR.parsePage(sheetSourceStream.stream());
    final var parsedDMR = parserDMR.parsePage(sheetSourceStream.stream());

    return Stream.of(parsedCN, parsedCT, parsedCR, parsedDMR)
        .flatMap(x -> x)
        .collect(Collectors.toList());
  }
}
