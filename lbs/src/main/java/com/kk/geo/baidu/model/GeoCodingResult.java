package com.kk.geo.baidu.model;

import com.kk.geo.baidu.model.element.GeoCodingItem;

/**
 * Geo API 返回的结果
 */
public class GeoCodingResult extends Result {
    private GeoCodingItem result; // 经纬度坐标

    public GeoCodingItem getResult() {
        return result;
    }

    public void setResult(GeoCodingItem result) {
        this.result = result;
    }
}
