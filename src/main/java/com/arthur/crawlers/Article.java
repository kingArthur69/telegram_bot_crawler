package com.arthur.crawlers;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Article {

	private String url;

	@Override
	public String toString() {
		return "Url: " + url;
	}
}
