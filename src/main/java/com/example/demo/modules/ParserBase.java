package com.example.demo.modules;

import com.example.demo.Util;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

abstract public class ParserBase {

  @Getter
  @Setter
  private SheetType sheetType = SheetType.CN;

  @Getter
  @Setter
  private HashMap<String, Document> document = new HashMap<>();

  public void setDocumentByUrl(Stream<String> url) throws IOException {
    document = url.map(element -> {
      final var inputStream = this.getClass().getClassLoader().getResourceAsStream(element);
      try {
        return new AbstractMap.SimpleEntry<>(element,
            Jsoup.parse(Util.readFromInputStream(inputStream)));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next,
        HashMap::new));
  }

  public Stream<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseElementByTag(
      String tag,
      String id) {
    final var content = document.entrySet().stream().map(
        element -> new AbstractMap.SimpleImmutableEntry<>(element.getKey(),
            new SimpleImmutableEntry<String, String>(id,
                element.getValue().select((String.format("[%s=%s]", tag, id))).text())));
    return content;
  }

  public Stream<AbstractMap.SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parsePage(
      Stream<SheetSource> sheetSourceStream) throws IOException {
    final var stream = sheetSourceStream.parallel()
        //element is a row in table sheetsource which is connected to sheetsource_sheetsource_type
        .filter(element -> element.getSheetSourceType().equals(this.getSheetType())).flatMap(
            (elementToBeParsed) -> parseElementByTag(elementToBeParsed.getHtmlTag(),
                elementToBeParsed.getHtmlID()));
    return stream;
  }

}
