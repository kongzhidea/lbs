package com.kk.geo;

import ch.hsr.geohash.GeoHash;
import com.alibaba.fastjson.JSON;
import com.kk.geo.baidu.model.GeoReverseLocationResult;
import com.kk.geo.baidu.service.BaiduMapService;

import java.math.BigDecimal;

public class GeoTest {
    public static void main(String[] args) {
        BaiduMapService baiduMapService = new BaiduMapService();
//        String lat = "39.912093958503";
//        String lng = "116.40393612824";
//
//        String centerHashCode = GeoHashUtil.toGeoHashBase32(lat, lng);
//        System.out.println(centerHashCode);
//
        GeoHash center = GeoHashUtil.fromGeohashString("wx4g2");
        GeoReverseLocationResult location = baiduMapService.parseLocation(center.getPoint().getLatitude(), center.getPoint().getLongitude());
        System.out.println(JSON.toJSON(location));
//
//        GeoHash tmp = center;
//        for (int i = 0; i < 6; i++) {
//            System.out.println(tmp.getEasternNeighbour().toBase32());
//            tmp = tmp.getEasternNeighbour();
//            baiduMapService.parseLocation(tmp.getBoundingBoxCenterPoint().getLatitude(), tmp.getBoundingBoxCenterPoint().getLongitude()).getResult();
//        }
//        System.out.println(".............................");
//
//        tmp = center;
//        for (int i = 0; i < 7; i++) {
//            System.out.println(tmp.getWesternNeighbour().toBase32());
//            tmp = tmp.getWesternNeighbour();
//            baiduMapService.parseLocation(tmp.getBoundingBoxCenterPoint().getLatitude(), tmp.getBoundingBoxCenterPoint().getLongitude()).getResult();
//        }
//        System.out.println(".............................");
//
//        tmp = center;
//        for (int i = 0; i < 6; i++) {
//            System.out.println(tmp.getNorthernNeighbour().toBase32());
//            tmp = tmp.getNorthernNeighbour();
//            baiduMapService.parseLocation(tmp.getBoundingBoxCenterPoint().getLatitude(), tmp.getBoundingBoxCenterPoint().getLongitude()).getResult();
//        }
//        System.out.println(".............................");
//
//
//        tmp = center;
//        for (int i = 0; i < 6; i++) {
//            System.out.println(tmp.getSouthernNeighbour().toBase32());
//            tmp = tmp.getSouthernNeighbour();
//            baiduMapService.parseLocation(tmp.getBoundingBoxCenterPoint().getLatitude(), tmp.getBoundingBoxCenterPoint().getLongitude()).getResult();
//        }
//        System.out.println(".............................");
    }
}
