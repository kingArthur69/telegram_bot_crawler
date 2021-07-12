package com.arthur.crawlers;

import com.arthur.crawlers.makler.MaklerSite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SiteCrawlerTest {

	@Test
	public void maklerCrawlTest() {
		final Site site = new MaklerSite();
		final SiteCrawler siteCrawler = new SiteCrawler(site);
		CrawlResult result = siteCrawler.crawl();

		assertEquals(22, result.getOptions().size());

		site.setCurrentUrl(site.getBaseUrl() + result.getOptions().get("Транспорт"));

		result = siteCrawler.crawl();

		assertEquals(16, result.getOptions().size());

		site.setCurrentUrl(site.getBaseUrl() + result.getOptions().get("Легковые автомобили"));

		result = siteCrawler.crawl();

		assertEquals(14, result.getOptions().size());

		Elements elements = Jsoup.parse(result.getOptions().get("Объявления")).select("li");

		Map<String, String> map = elements.stream().collect(Collectors.toMap(Element::text, element -> {
			Elements input = element.select("input");
			return input.attr("name") + "=" + input.val();
		}));

		System.out.println("Available categories: " + elements.text());

		site.setCurrentUrl(site.getCurrentUrl() + "?list&" + map.get("интернет"));

		result = siteCrawler.crawl();

		assertEquals(16, result.getOptions().size());
	}
}