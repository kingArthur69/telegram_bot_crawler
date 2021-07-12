package com.arthur.crawlers.makler;

import com.arthur.crawlers.CrawlResult;
import com.arthur.crawlers.Site;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MaklerSite extends Site {

	public static final Pattern PATTERN = Pattern.compile("<\\/?[a-z][\\s\\S]*>");
	private static final String BASE_URL = "https://makler.md";
	private static final String LINK_ATTR_SELECTOR = "href";
	private static final String OPTIONS_CSS_SELECTOR = "ul[class=tlist rublist menuAim] li a";
	private static final String FILTERS_CSS_SELECTOR = "div#filtersContainer a.ftitle";
	private static final String OBJECTS_CSS_SELECTOR = "article";
	private static final String FILTERS_OPTIONS_CSS_SELECTOR = "div.fbody";

	public MaklerSite() {
		setBaseUrl(BASE_URL);
		setCurrentUrl(BASE_URL);
	}

	@Override
	public CrawlResult parse(Document document, String[] strings) {

		CrawlResult result = new MaklerCrawlResult();

		result.setOptions(parseOptions(document));

		Map<String, String> foundObjects = document.select(OBJECTS_CSS_SELECTOR).stream()
				.collect(Collectors.toMap(element -> UUID.randomUUID().toString(), Element::text));

		result.setFoundObjects(foundObjects);

		return result;
	}

	private Map<String, String> parseOptions(Document document) {

		if (document.select(OPTIONS_CSS_SELECTOR).first() != null) {
			return document.select(OPTIONS_CSS_SELECTOR).stream()
					.collect(Collectors.toMap(Element::text, element -> element.attr(LINK_ATTR_SELECTOR)));
		}

		return document.select(FILTERS_CSS_SELECTOR).stream()
				.collect(Collectors.toMap(Element::text, element -> element.parent().select(FILTERS_OPTIONS_CSS_SELECTOR).toString()));
	}

	@Override
	public boolean adjustUrl(CrawlResult result, String parameter) {
		boolean urlChanged = false;
		if (result.getOptions() != null
				&& StringUtils.isNotBlank(result.getOptions().get(parameter))) {

			String option = result.getOptions().get(parameter);

			if (PATTERN.matcher(option).find()) {
				Map<String, String> options = Jsoup.parse(option).select("li").stream().collect(Collectors.toMap(Element::text, element -> {
					Elements input = element.select("input");
					return input.attr("name") + "=" + input.val();
				}));

				result.setOptions(options);

			} else if (StringUtils.contains(option, "=")) {

				if (StringUtils.contains(getCurrentUrl(), "?list&")) {
					setCurrentUrl(getCurrentUrl() + "&" + option);
				} else {
					setCurrentUrl(getCurrentUrl() + "?list&" + option);
				}
				urlChanged = true;

			} else {
				setCurrentUrl(BASE_URL + option);
				urlChanged = true;
			}
		}
		return urlChanged;
	}
}
