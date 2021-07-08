package com.arthur;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static final String TEST_URL = "https://makler.md/ru/transport/cars?list&city[]=1112&city[]=1113&city[]=1250&kind[]=sell&field_469[]=3127&field_159[]=1186&field_159[]=1586&field_159[]=1587&field_159[]=1588&field_159[]=1589&field_159[]=1590&field_159[]=1591&field_159[]=1592&field_159[]=1593&field_159[]=2444&field_159[]=2445&field_159[]=1594&field_159[]=1595&field_159[]=2446&field_159[]=2447&field_159[]=1596&field_159[]=1597&field_159[]=2448&field_159[]=1598&field_159[]=1599&field_159[]=1600&field_159[]=4630&field_159[]=1601&field_159[]=1602&field_159[]=1603&field_159[]=4707&field_159[]=1604&field_159[]=1605&field_159[]=2449&field_159[]=1606&field_159[]=1607&field_159[]=1608&field_159[]=2450&field_159[]=2451&field_159[]=2452&field_159[]=2453&field_159[]=2454&field_159[]=1609&field_159[]=2455&field_159[]=1610&field_159[]=2456&field_159[]=1611&field_159[]=1612&field_159[]=2457&field_159[]=2458&field_159[]=1613&field_159[]=2459&field_159[]=1614&field_159[]=2460&field_159[]=2461&field_159[]=1615&field_159[]=1616&field_315_max=2000&field_212[]=720&field_319[]=1401&price_max=2000&currency_id=5&list=detail";

    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect(TEST_URL).get();
            Elements element = document.select("div#filtersContainer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
