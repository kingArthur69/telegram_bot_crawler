package com.arthur.crawlers;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CrawlerService {

//	private SiteCrawler crawler;
//
//	public CrawlerService(SiteCrawler crawler) {
//		this.crawler = crawler;
//	}

	public CrawlResult crawl(String url) {
		SiteCrawler crawler = new SiteCrawler(url);
		return crawler.crawl();
	}

	public CrawlResult crawl(String[] strings) {
		SiteCrawler crawler = new SiteCrawler(strings[0]);
		List<String> stringList = Arrays.stream(strings).map(string -> RegExUtils.replaceFirst(string, "_", " ")).collect(Collectors.toList());
		return crawler.crawl(stringList);
	}

	public String showOptions(CrawlResult crawlResult) {
		return StringUtils.join(crawlResult.getOptions().keySet(), "\n");
	}

	public String showFoundObjects(CrawlResult crawlResult) {
		return StringUtils.substring(StringUtils.join(crawlResult.getFoundObjects().values(), "\n"), 0, 200);
	}
}
