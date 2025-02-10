package com.example.web.util;

import java.util.HashMap;
import java.util.Map;

public enum NewsTypeEnumUtil {
    FINANCES("1", "金融"),
    DINING("2", "餐饮"),
    TECHNOLOGY("3", "科技"),
    LIFE("4", "生活"),
    ENTERTAINMENT("5", "娱乐"),
    KNOWLEDGE("6", "知识"),
    OTHER("7", "其他");

    private String newsType;

    private String code;

    private static final Map<String, String> map = new HashMap<>();

    static {
        for (NewsTypeEnumUtil e: NewsTypeEnumUtil.values()) {
            map.put(e.code, e.newsType);
        }
    }

    NewsTypeEnumUtil(String code, String newsType) {
        this.newsType = newsType;
        this.code = code;
    }

    public String getNewsType() {
        return newsType;
    };

    public static String getNewsTypeUtil(String code) {
        return map.get(code);
    }
}
