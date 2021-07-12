package com.arthur.crawlers;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public abstract class CrawlResult {

    private Map<String, String> options;

    private List<Article> foundObjects;
}
