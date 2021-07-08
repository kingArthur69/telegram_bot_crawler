package com.arthur.crawlers.makler;

import com.arthur.crawlers.Site;
import com.arthur.crawlers.SiteCrawler;
import org.jsoup.nodes.Document;

import java.util.Map;

public class MaklerSiteCrawlerImpl extends SiteCrawler {
    public MaklerSiteCrawlerImpl(Site site) {
        super(site);
    }

    @Override
    protected Map<String, String> parseFoundObjects(Document document) {
        return super.parseFoundObjects(document);
    }

    @Override
    protected Map<String, String> parseOptions(Document document) {
        return super.parseOptions(document);
    }
}
