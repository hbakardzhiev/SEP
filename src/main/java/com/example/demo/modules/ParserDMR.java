package com.example.demo.modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/** Creates parses that parses DMR pages */
public class ParserDMR extends ParserBase {

  /**
   * Takes the CT parses and parses the DMR pages
   *
   * @param parserCT the parses that parses CT
   * @throws IOException
   */
  public ParserDMR(ParserCT parserCT) throws IOException {
    setSheetType(SheetType.CT);
    passCT(parserCT);
  }

  /**
   * @param parserCT
   * @throws IOException
   */
  private void passCT(ParserCT parserCT) throws IOException {
    final var documents = parserCT.getDocument().values().parallelStream();
    // TODO: change the mask here
    final var stream = documents.map(element -> element.select(
        //id=table chnagetask result and so on is the right div in the page which has as children the <a> tags
        "[id=table__changeTask_resultingItems_table_TABLE] a:matchesOwn((^D[\\d]{9})|(^EngPartNr[\\d]{3}))"));
    final var listStrings =
        stream
            .flatMap(Collection::stream)
            .map(element -> element.attr("href"))
            .collect(Collectors.toCollection(ArrayList::new));
    this.setDocumentByUrl(listStrings.parallelStream());
    listStrings.forEach(System.out::println);
  }
}
