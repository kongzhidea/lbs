package com.kk.geo.baidu.model.element;

/**
 * 搜索地址 返回结果
 */
public class SearchItem {
    private String name;// 名称
    private Location location; // 经纬度
    private String address;// 地址

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
