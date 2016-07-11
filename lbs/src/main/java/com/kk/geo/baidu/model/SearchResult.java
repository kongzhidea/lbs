package com.kk.geo.baidu.model;

import com.kk.geo.baidu.model.element.SearchItem;

import java.util.List;

/**
 * 搜索地址 返回结果
 */
public class SearchResult extends Result {
    private String message;
    private List<SearchItem> results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SearchItem> getResults() {
        return results;
    }

    public void setResults(List<SearchItem> results) {
        this.results = results;
    }
}
