package com.arthur.crawlers;

import lombok.Data;

@Data
public abstract class Site {

    private String baseUrl;
    private String url;
    private CrawlResult result;
    private String optionsSelector;
    private String linkAttrSelector;
    private String objectsSelector;
    private String objectsIdSelector;
}
