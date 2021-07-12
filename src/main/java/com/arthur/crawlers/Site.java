package com.arthur.crawlers;

import lombok.Data;
import org.jsoup.nodes.Document;

@Data
public abstract class Site {

    private String baseUrl;
	private String currentUrl;

	public abstract CrawlResult parse(Document document, String... args);

	public boolean adjustUrl(CrawlResult result, String parameter) {
		return true;
	}
}
