package com.arthur.crawlers;

import com.arthur.crawlers.makler.MaklerSite;
import org.apache.commons.lang3.StringUtils;

public class SiteSwitcher {

    static Site getSite(String siteUrl) {
        if (StringUtils.contains(siteUrl, "makler")) {
            return new MaklerSite();
        } else {
            return null;
        }
    }
}
