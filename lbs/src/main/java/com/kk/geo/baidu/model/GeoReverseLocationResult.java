package com.kk.geo.baidu.model;

import com.kk.geo.baidu.model.element.GeoReverseLocationItem;

/**
 * 根据 经纬度 逆解析地址信息
 */
public class GeoReverseLocationResult extends Result {
    private GeoReverseLocationItem result;

    public GeoReverseLocationItem getResult() {
        return result;
    }

    public void setResult(GeoReverseLocationItem result) {
        this.result = result;
    }
}
