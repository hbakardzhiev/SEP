package com.example.demo.modules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import static com.example.demo.Util.*;

/**
 * We follow somewhat the State design pattern by creating a base abstract parser from which we
 * create further classes.
 */
public abstract class ParserBase {

    @Getter
    @Setter
    private SheetType sheetType = SheetType.CN;

    public static String sandboxFolder;
    /**
     * Dictionary which holds the unique name of the page eg. CR000001, ProjectName01 - SW Tooling,
     * E0011 LocationId002 as key and the parsed HTML content as key
     */
    @Getter
    @Setter
    private HashMap<String, Document> document = new HashMap<>();

    /**
     * Takes the stream of urls eg. locations of the webpages, goes through each of them and saves
     * them in the document hashmap.
     *
     * @param url Streams of urls on which the page is saved on
     * @throws IOException
     */
    public void setDocumentByUrl(Stream<String> url) {
        document = url.filter(element -> element != null && !element.equals("") && !element.equals(EXTERNAL_PAGE)).map(element -> {
            final var tempName = switch (sheetType) {
                case CN -> CHANGE_NOTICE_EXAMPLE_HTML;
                case CR, CT, DMR -> element;
            };
            if (element.matches("^CN[\\d]{6}$")) {
                sandboxFolder = element;
            }
            try {
                final var path = Paths.get(RESOURCE_LOCATION, sandboxFolder, tempName);
                final var currentDocument = Jsoup.parse(Files.readString(path));
                return new SimpleEntry<>(readDocumentName(currentDocument), currentDocument);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));
    }

    /**
     * Goes through the page and finds all HTML elements that satisfy the tag, id capture. Returns the
     * pair of unique name of the page with value (id on the page with key the html value of the
     * element).
     *
     * @param tag the attribute of the html tag eg. "attrid" or "id", etc.
     * @param id  the value of the tag
     * @return Stream of Key Value pairs which hold Key Value pairs
     */
    private Stream<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseElementByTag(String tag, String id) {
        final var content = document.entrySet().parallelStream().map(element -> new SimpleImmutableEntry<>(element.getKey(), new SimpleImmutableEntry<String, String>(id, element.getValue().select((String.format("[%s=%s]", tag, id))).text())));
        return content;
    }

    /**
     * Takes all elements from the sheetSource filters only the relevant ones for the page parses them
     * and returns usable data in the form of Stream
     *
     * @param sheetSourceStream Stream of SheetSources tells it which page to gather
     * @return key - unique name of the document, value - key, value pair that holds the id and text
     * value
     * @throws IOException
     */
    public Stream<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parsePage(Stream<SheetSource> sheetSourceStream) {
        final var stream = sheetSourceStream.parallel()
                // element is a row in table sheet_source
                .filter(element -> element.getSheetSourceType().equals(this.getSheetType())).flatMap((elementToBeParsed) -> parseElementByTag(elementToBeParsed.getHtmlTag(), elementToBeParsed.getHtmlID()));
        return stream;
    }

    private String readDocumentName(Document document) {
        final var firstPart = document.select("[id=infoPageIdentityDisplayType]").text();
        final var secondPart = document.select("[id=infoPageIdentityObjectIdentifier]").text();
        return firstPart + " - " + secondPart;
    }
}
