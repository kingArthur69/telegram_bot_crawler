package com.arthur.crawlers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SiteCrawler {

    public static final Logger LOGGER = Logger.getLogger(SiteCrawler.class.getName());

    private Site site;

    public SiteCrawler(Site site) {
        this.site = site;
    }

    CrawlResult crawl() {
        CrawlResult result = site.getResult();
        try {
            Document document = Jsoup.connect(site.getUrl()).get();

            Map<String, String> options = parseOptions(document);

            result.setOptions(options);

            Map<String, String> foundObjects = parseFoundObjects(document);

            result.setFoundObjects(foundObjects);

        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return result;
    }

    protected Map<String, String> parseFoundObjects(Document document) {
        return document.select(site.getObjectsSelector()).stream()
                .collect(Collectors.toMap(element -> UUID.randomUUID().toString(), Element::text));
    }

    protected Map<String, String> parseOptions(Document document) {
        return document.select(site.getOptionsSelector()).stream()
                .collect(Collectors.toMap(Element::text, element -> element.attr(site.getLinkAttrSelector())));
    }
}
