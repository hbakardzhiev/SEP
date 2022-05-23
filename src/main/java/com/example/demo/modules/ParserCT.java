package com.example.demo.modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ParserCT extends ParserBase {

  public ParserCT(ParserCN parserCN) throws IOException {
    setSheetType(SheetType.CT);
    passCN(parserCN);
  }

  private void passCN(ParserCN parserCN) throws IOException {
    final var stream = parserCN.getDocument().values().parallelStream()
        // ? are for optional M, D, E which would match any string like MCT, MDCT, ECT, CT followed by 6 numbers.
        .map(element -> element.select("a:matchesOwn(^([M]?[D]?[E]?CT[\\d]{6}))"));
    final var listStrings = stream.parallel().flatMap(Collection::stream)
        .map(element -> element.attr("href")).collect(Collectors.toCollection(ArrayList::new));
    this.setDocumentByUrl(listStrings.parallelStream());
  }

}
