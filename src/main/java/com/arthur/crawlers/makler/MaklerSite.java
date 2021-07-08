package com.arthur.crawlers.makler;

import com.arthur.crawlers.Site;

public class MaklerSite extends Site {

    public static final String BASE_URL = "https://makler.md";
    public static final String OPTIONS_CSS_SELECTOR = "div#filtersContainer";
    public static final String LINK_ATTR_SELECTOR = "href";

    public MaklerSite() {
        setBaseUrl(BASE_URL);
        setUrl(BASE_URL);
        setResult(new MaklerCrawlResult());
        setOptionsSelector("ul[class=tlist rublist menuAim] li a");
        setLinkAttrSelector(LINK_ATTR_SELECTOR);
        setObjectsSelector("article");
        setObjectsIdSelector("id");
    }
}
