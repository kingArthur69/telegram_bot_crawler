package com.arthur.crawlers.makler;

import com.arthur.crawlers.CrawlResult;
import com.arthur.crawlers.MockHtmlPageProvider;
import com.arthur.crawlers.Site;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MaklerSiteTest {

	private Site site;

	@BeforeEach
	void setUp() {
		site = new MaklerSite();
	}

	@Test
	void MaklerSiteParserTest() throws IOException {
		CrawlResult result = site.parse(MockHtmlPageProvider.getResource("/com/arthur/crawlers/makler/Makler_MainPage.html"));

		assertAll(
				() -> assertEquals(22, result.getOptions().size()),
				() -> assertEquals(0, result.getFoundObjects().size()),
				() -> assertTrue(result instanceof MaklerCrawlResult)
		);
	}

	@Test
	void MaklerSite_PageWithFilers_Test() throws IOException {
		CrawlResult result = site.parse(MockHtmlPageProvider.getResource("/com/arthur/crawlers/makler/Makler_PageWithFilters.html"));

		assertAll(
				() -> assertEquals(14, result.getOptions().size()),
				() -> assertEquals(100, result.getFoundObjects().size()),
				() -> assertTrue(result instanceof MaklerCrawlResult)
		);
	}
}