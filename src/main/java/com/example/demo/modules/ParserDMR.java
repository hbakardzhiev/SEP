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
   * @param documents the parses that parses CT
   * @throws IOException
   */
  public ParserDMR(Stream<Document> documents) throws IOException {
    setSheetType(SheetType.CT);
    passCN(documents);
  }

  /**
   * @param documents
   * @throws IOException
   */
  private void passCN(Stream<Document> documents) throws IOException {
    // TODO: change the mask here
    final var stream = documents.map(element -> element.select("a:matchesOwn(^ECT[\\d]{6})"));
    final var listStrings =
        stream
            .flatMap(Collection::stream)
            .map(element -> element.attr("href"))
            .collect(Collectors.toCollection(ArrayList::new));
    this.setDocumentByUrl(listStrings.parallelStream());
  }
}
