package com.example.demo.modules;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Creates parses that parses DMR pages
 */
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
     * Sets the dmr pages urls to the class by going through the parent pages of CT
     *
     * @param parserCT is the parser of CT
     * @throws IOException
     */
    private void passCT(ParserCT parserCT) throws IOException {
        final var documents = parserCT.getDocument().values().parallelStream();
        // TODO: add more types if necessary
        final var stream = documents.map(element -> element.select(
                // div with id=table changetask result and so on is the right div in the page
                // which has as children the <a> tags
                "div[id=table__changeTask_resultingItems_table_TABLE]" + " a:matchesOwn((^D[\\d]{9})|(^EngPartNr[\\d]{3})|(^[\\d]{12}))")).flatMap(Collection::stream);
        final var listStrings = stream.map(element -> element.attr("href"));
        this.setDocumentByUrl(listStrings);
    }
}
