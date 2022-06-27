package com.example.demo.services;

import com.example.demo.modules.ParserCN;
import com.example.demo.modules.ParserCR;
import com.example.demo.modules.ParserCT;
import com.example.demo.modules.ParserDMR;
import com.example.demo.repository.SheetSourceRepository;
import java.io.IOException;
import java.time.OffsetDateTime;
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
   * Combines all parsed pages in one list. Then turns them into a stream and also returns the
   * timestamp of the parsing of data
   *
   * @return List of the parsed Cn, CR, Cts, DMRs pages
   * @throws IOException
   */
  public SimpleImmutableEntry<
          List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>>, OffsetDateTime>
      parseEverything(String input) throws IOException {
    final var sheetSourceStream = sheetSourceRepository.findAll();
    final var parserCN = new ParserCN(input);
    final var parserCT = new ParserCT(parserCN);
    final var parserCR = new ParserCR(parserCN);
    final var parserDMR = new ParserDMR(parserCT);
    final var offsetTime = OffsetDateTime.now();

    final var parsedCN = parserCN.parsePage(sheetSourceStream.parallelStream());
    final var parsedCT = parserCT.parsePage(sheetSourceStream.parallelStream());
    final var parsedCR = parserCR.parsePage(sheetSourceStream.parallelStream());
    final var parsedDMR = parserDMR.parsePage(sheetSourceStream.parallelStream());

    return new SimpleImmutableEntry<>(
        Stream.of(parsedCN, parsedCT, parsedCR, parsedDMR)
            .flatMap(x -> x)
            .collect(Collectors.toList()),
        offsetTime);
  }
}
