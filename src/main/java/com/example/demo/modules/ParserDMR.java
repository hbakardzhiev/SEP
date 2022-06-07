package com.example.demo.modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jsoup.nodes.Document;

/** Creates parses that parses DMR pages */
public class ParserDMR extends ParserBase {

  /**
   * Takes the CT parses and parses the DMR pages
   *
   * @param parserCT the parses that parses CT
   * @throws IOException
   */
  public ParserDMR(ParserCT parserCT) throws IOException {
    setSheetType(SheetType.DMR);
    passCT(parserCT);
  }

  /**
   * @param parserCT
   * @throws IOException
   */
  private void passCT(ParserCT parserCT) throws IOException {
    // TODO: change the mask here
    final var stream = parserCT.getDocument().values().stream().map(element -> element.select("a:matchesOwn(^ECT[\\d]{6})"));
    final var listStrings =
        stream
            .flatMap(Collection::stream)
            .map(element -> element.attr("href"))
            .collect(Collectors.toCollection(ArrayList::new));
    this.setDocumentByUrl(listStrings.parallelStream());
  }
}
