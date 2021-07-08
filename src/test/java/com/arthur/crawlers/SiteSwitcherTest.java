package com.arthur.crawlers;

import com.arthur.crawlers.makler.MaklerSite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteSwitcherTest {

    @Test
    public void getMaklerTest() {
        assertTrue(SiteSwitcher.getSite("www.makler.md") instanceof MaklerSite);
    }

    @Test
    public void getNullTest() {
        assertNull(SiteSwitcher.getSite("testUrl"));
    }
}