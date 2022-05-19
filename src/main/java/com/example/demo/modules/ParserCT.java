package com.example.demo.modules;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Element;

public class ParserCT extends ParserBase {

  public ParserCT(ParserCN parserCN) throws IOException {
    setSheetType(SheetType.CT);
    passCN(parserCN);
  }

  private void passCN(ParserCN parserCN) throws IOException {
    final var stream = parserCN.getDocument().values().parallelStream()
        .map(element -> element.select("a:matchesOwn(^ECT[\\d]{6})"));
    var listStrings = new ArrayList<String>();
    stream.forEach(elements -> {
      for (Element element : elements) {
        listStrings.add(element.attr("href"));
      }
    });
    this.setDocumentByUrl(listStrings.parallelStream());
  }

}
