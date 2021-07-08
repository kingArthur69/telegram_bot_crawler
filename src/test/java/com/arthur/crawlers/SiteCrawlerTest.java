package com.arthur.crawlers;

import com.arthur.crawlers.makler.MaklerCrawlResult;
import com.arthur.crawlers.makler.MaklerSite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SiteCrawlerTest {

    @Test
    public void maklerCrawlTest() {
        final Site site = new MaklerSite();
        final SiteCrawler siteCrawler = new SiteCrawler(site);
        final CrawlResult result = siteCrawler.crawl();

        assertAll(
                () -> assertEquals(22, result.getOptions().size()),
                () -> assertTrue(result instanceof MaklerCrawlResult)
        );

        site.setUrl(site.getBaseUrl() + result.getOptions().get("Транспорт"));

        final CrawlResult result2 = siteCrawler.crawl();

        assertAll(
                () -> assertEquals(22, result2.getOptions().size()),
                () -> assertTrue(result2 instanceof MaklerCrawlResult)
        );


    }
}