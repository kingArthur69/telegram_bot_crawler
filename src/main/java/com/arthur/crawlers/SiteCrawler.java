package com.arthur.crawlers;

import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

@Log4j
public class SiteCrawler {

	private Site site;

	public SiteCrawler(String url) {
		this.site = SiteSwitcher.getSite(url);
	}

	public SiteCrawler(Site site) {
		this.site = site;
	}

	CrawlResult crawl() {
		CrawlResult result = null;
		try {
			Document document = Jsoup.connect(site.getCurrentUrl()).get();

			result = site.parse(document);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	CrawlResult crawl(List<String> strings) {

		CrawlResult result = null;
		for (String parameter : strings) {
			try {
				if (result == null || site.adjustUrl(result, parameter)) {
					log.info("Crawling: " + site.getCurrentUrl());
					Document document = Jsoup.connect(site.getCurrentUrl()).get();
					result = site.parse(document, parameter);
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		return result;
	}
}
