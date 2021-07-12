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

	public String showOptionsNames(CrawlResult crawlResult) {
		return StringUtils.join(crawlResult.getOptions().keySet(), "\n");
	}

	public List<String> showOptionsKeyboard(CrawlResult crawlResult, String command, String[] strings) {
		String sendParameters = Arrays.stream(strings)
				.map(s -> replaceSpaces(s))
				.collect(Collectors.joining(" "));

		String previousCommand = "/" + command + " " + sendParameters + " ";
		return crawlResult.getOptions().keySet().stream().map(entry -> previousCommand + replaceSpaces(entry))
				.collect(Collectors.toList());
	}

	private String replaceSpaces(String s) {
		return RegExUtils.replaceAll(s, " ", "_");
	}
}
