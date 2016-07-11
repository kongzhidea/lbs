package com.kk.geo.baidu.model;


import com.kk.geo.baidu.model.element.Location;

/**
 * 经纬度
 */
public class Coordinate {
    private String longitude; // 经度
    private String latitude; // 纬度

    public Coordinate() {
    }

    public Coordinate(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Coordinate(Location location) {
        this.latitude = String.valueOf(location.getLat());
        this.longitude = String.valueOf(location.getLng());
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
