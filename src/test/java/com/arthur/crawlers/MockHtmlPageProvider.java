package com.arthur.crawlers;

import org.apache.commons.codec.Charsets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class MockHtmlPageProvider {

	public static Document getResource(String path) throws IOException {
		return Jsoup.parse(new File(MockHtmlPageProvider.class.getResource(path).getFile()), Charsets.UTF_8.toString());
	}

}
