package com.kk.geo;

import ch.hsr.geohash.GeoHash;
import org.apache.commons.lang.StringUtils;

public class GeoHashUtil {
    // 经纬度 总编码长度，  toBasw32时候，限制是5的倍数
    public static final int NUMBER_OF_BITS = 25;

    // 经纬度 转换为 geocode
    public static String toGeoHashBase32(double latitude, double longitude) {
        GeoHash hash = GeoHash.withBitPrecision(latitude, longitude, NUMBER_OF_BITS);
        return hash.toBase32();
    }

    public static String toGeoHashBase32(String latitude, String longitude) {
        if (StringUtils.isNotBlank(latitude) && StringUtils.isNotBlank(longitude)) {
            return toGeoHashBase32(Double.parseDouble(latitude), Double.parseDouble(longitude));
        }
        return null;
    }

    // geocode 逆解析
    public static GeoHash fromGeohashString(String geoCode) {
        return GeoHash.fromGeohashString(geoCode);
    }

    public static void main(String[] args) {

        String geoCode = toGeoHashBase32("39.92324", "116.3906");
        System.out.println(geoCode);

        GeoHash geoHash = fromGeohashString(geoCode); // "wx4g0ec1"
        System.out.println(Long.toBinaryString(geoHash.longValue())); // 1110011101001000111100000011010101100001000000000000000000000000

        // fromGeohashString 时候，point是min+max/2，等于 centerPoint
        System.out.println(geoHash.getPoint());// 传入参数的 经纬度
        System.out.println(geoHash.getBoundingBoxCenterPoint()); // geoCode区域中心点 的经纬度

        GeoHash northGeoHash = geoHash.getNorthernNeighbour();
        System.out.println(northGeoHash.getBoundingBoxCenterPoint()); // 北部区域


    }
}
