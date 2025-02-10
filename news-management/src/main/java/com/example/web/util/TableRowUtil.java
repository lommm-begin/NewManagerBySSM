package com.example.web.util;

import com.example.web.pojo.AllNews;

import java.util.*;

public class TableRowUtil {

    private static Set<String> rows = new HashSet<>(Arrays.asList("title", "author", "age"));

    /**
     * 查找列是否存在
     */
    public static String judgementRow(String row) {
        if (row != null) {
            if (rows.contains(row.toLowerCase())) {
                return row;
            }
        }

        return "all";
    }
}
