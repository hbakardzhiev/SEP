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
        .map(element -> element.select("a:matchesOwn(^ECT[\\d]{6})"));
    final var listStrings = stream.parallel().flatMap(Collection::stream)
        .map(element -> element.attr("href")).collect(Collectors.toCollection(ArrayList::new));
    this.setDocumentByUrl(listStrings.parallelStream());
  }

}
