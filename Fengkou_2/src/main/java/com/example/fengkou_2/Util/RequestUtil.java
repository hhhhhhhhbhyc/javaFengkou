package com.example.fengkou_2.Util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {
    /**
     * 爬虫方法
     * @param Url
     */
    public static Document test(String Url){
        String url=Url;
        try {
            Document document = Jsoup.parse(new URL(url),30000);
            System.out.println(document);
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 一个测试方法，目前有用了
     * @param Url
     * @return
     */
    public static Document bigtest(String Url){
        Connection connect = Jsoup.connect(Url);
        Map<String, String> header = new HashMap<String, String>();

        header.put("User-Agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
        header.put("Accept-Language", "zh-cn,zh;q=0.5");
        header.put("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
        header.put("Connection", "keep-alive");
        header.put("operationName","nojGlobalData");
        Connection data = connect.headers(header);
        try {
            Document document = data.get();
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
