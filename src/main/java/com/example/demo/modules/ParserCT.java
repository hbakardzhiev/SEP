package com.example.demo.modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/** Creates parses that parses CT pages */
public class ParserCT extends ParserBase {

  /**
   * Takes the CN parses and parses the CT pages
   *
   * @param parserCN the parses that parses CN
   * @throws IOException
   */
  public ParserCT(ParserCN parserCN) throws IOException {
    setSheetType(SheetType.CT);
    passCN(parserCN);
  }

  /**
   * Goes through the CN web page takes the CT links and sets the urls in CT.
   *
   * @param parserCN the parses that parses CN
   * @throws IOException
   */
  private void passCN(ParserCN parserCN) throws IOException {
    final var stream =
        parserCN.getDocument().values().parallelStream()
            // ? are for optional M, D, E which would match any string like MCT, MDCT, ECT, CT
            // followed by 6 numbers.
            .map(element -> element.select("a:matchesOwn(^([M]?[D]?[E]?CT[\\d]{6}))"));
    final var listStrings =
        stream
            .parallel()
            .flatMap(Collection::stream)
            .map(element -> element.attr("href"))
            .collect(Collectors.toCollection(ArrayList::new));
    this.setDocumentByUrl(listStrings.parallelStream());
  }
}
