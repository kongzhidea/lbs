package com.kk.geo.baidu.service;

import com.alibaba.fastjson.JSONObject;
import com.kk.geo.baidu.model.*;
import com.kk.geo.baidu.model.element.Location;
import com.kk.util.HttpClientUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 依赖 httpclient,fastjson
 */
public class BaiduMapService {
    public static final Log logger = LogFactory.getLog(BaiduMapService.class);

    // 获取地址 经纬度
    private String geoCodingUrl = "http://api.map.baidu.com/geocoder/v2/?ak=%s&output=%s&city=%s&address=%s";

    // 位置搜索
    private String geoSearchUrl = "http://api.map.baidu.com/place/v2/search?ak=%s&output=%s&region=%s&q=%s";

    // 根据Ip 解析城市信息
    private String ipLocationUrl = "http://api.map.baidu.com/location/ip?ak=%s&ip=%s";

    // 根据经纬度 逆解析 地址信息  pois:是否显示指定位置周边的poi，0为不显示，1为显示。当值为1时，显示周边100米内的poi。默认0
    private String geoLocationReverseUrl = "http://api.map.baidu.com/geocoder/v2/?ak=%s&location=%s&output=%s&pois=0";

    private String geoAk = "6n1ItqpnT5rLKZs9jM4PEGPk";

    private String routeMatrixUrl = "http://api.map.baidu.com/direction/v1/routematrix?ak=%s&output=%s&origins=%s&destinations=%s";

    private String dataType = "json";


    /**
     * 解析地址，得到经纬度，  结合 Geo Coding Api和Place Api  推荐！！！
     *
     * @param address  推荐使用全地址, 包含市和区
     * @param city
     * @param district
     * @return
     */
    public Coordinate parseAddress(String address, String city, String district) {
        try {
            address = address.replaceAll(" ", "");
            Pattern pattern = Pattern.compile("\\d+单元");
            Matcher matcher = pattern.matcher(address);
            if (matcher.find()) {
                address = address.substring(0, matcher.start());
            }

            Location location = null;
            GeoCodingResult geoCodingResult = parseAddress(city, address);
            if (geoCodingResult.getResult().getPrecise() == 1) {
                location = geoCodingResult.getResult().getLocation();
            } else {
                if (StringUtils.isNotBlank(district)) {
                    if (address.indexOf(district) == -1) {
                        address = district + address;
                    }
                }
                SearchResult searchResult = searchAddress(city, address);
                if (CollectionUtils.isNotEmpty(searchResult.getResults())) {
                    location = searchResult.getResults().get(0).getLocation();
                }
            }
            if (location == null) {
                return null;
            }
            return new Coordinate(location);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取 经纬度
     * <p/>
     * http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
     *
     * @param city    城市，如北京市
     * @param address 地址信息，如 海淀区宝盛广场
     * @return
     */
    public GeoCodingResult parseAddress(String city, String address) {
        if (address == null) {
            return null;
        }
        if (city == null) {
            city = "";
        }

        if (address.indexOf(city) == -1) {
            address = city + address;
        }
        logger.info("url: " + geoCodingUrl + ", geoAk: " + geoAk + ", dataType: " + dataType);
        String getUrl = String.format(geoCodingUrl, geoAk, dataType, city, address);
        try {
            String ret = HttpClientUtil.sendGet(getUrl);
            logger.info("sent baidu get url: " + getUrl + ", result: " + ret);
            return JSONObject.parseObject(ret, GeoCodingResult.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 根据 经纬度 逆解析地址信息
     * <p/>
     * http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
     *
     * @param lat 纬度
     * @param lng 经度
     * @return
     */
    public GeoReverseLocationResult parseLocation(double lat, double lng) {
        return parseLocation(String.valueOf(lat), String.valueOf(lng));

    }

    public GeoReverseLocationResult parseLocation(String lat, String lng) {
        StringBuilder location = new StringBuilder();
        location.append(lat);
        location.append(",");
        location.append(lng);
        String getUrl = String.format(geoLocationReverseUrl, geoAk, location.toString(), dataType);
        try {
            String ret = HttpClientUtil.sendGet(getUrl);
            logger.info("sent baidu get url: " + getUrl + ", result: " + ret);
            return JSONObject.parseObject(ret, GeoReverseLocationResult.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 地区搜索，返回符合条件的地区列表，包含经纬度
     * <p/>
     * http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-placeapi
     *
     * @param city
     * @param address
     * @return
     */
    public SearchResult searchAddress(String city, String address) {
        String getUrl = String.format(geoSearchUrl, geoAk, dataType, city, address);
        try {
            String ret = HttpClientUtil.sendGet(getUrl);
            logger.info("sent baidu get url: " + getUrl + ", result: " + ret);
            return JSONObject.parseObject(ret, SearchResult.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据ip地址获取所在城市
     * <p/>
     * http://lbsyun.baidu.com/index.php?title=webapi/ip-api
     */
    public IpLocationResult parseCityByIp(String ip) {
        if (ip == null || "".equals(ip)) {
            return null;
        }
        String _ipLocationUrl = String.format(this.ipLocationUrl, geoAk, ip);
        try {
            String ret = HttpClientUtil.sendGet(_ipLocationUrl);
            logger.info("sent baidu get url: " + _ipLocationUrl + ", result: " + ret);
            return JSONObject.parseObject(ret, IpLocationResult.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 计算两地之间的 距离以及 (默认驾车)到达时间
     *
     *
     * @param origin      名称：百度大厦
     *                    经纬度：40.056878, 116.30815 坐标格式为：lat<纬度>,lng<经度>
     * @param destination 名称：天安门
     *                    经纬度：39.915285, 116.403857 坐标格式为：lat<纬度>,lng<经度>
     * @return
     */
    public RouteResult calcDistance(String origin, String destination) {
        String url = String.format(routeMatrixUrl, geoAk, dataType, origin, destination);
        String ret = HttpClientUtil.sendGet(url);

        return JSONObject.parseObject(ret, RouteResult.class);
    }


    public static void main(String[] args) {
        BaiduMapService map = new BaiduMapService();

        System.out.println("...............geo coding ................");
        // 昌平区国仕汇小区  "lat":40.086001731544,"lng":116.32017693224  precise=1
//        GeoCodingResult geoCodingResult = map.parseAddress("北京市", "昌平区国仕汇小区");
        // 海淀区宝盛广场 "lat":40.033162045078,"lng":116.23967780102 precise=0
        GeoCodingResult geoCodingResult = map.parseAddress("北京市", "海淀区宝盛广场");
        System.out.println(JSONObject.toJSON(geoCodingResult));
        System.out.println("................end geo coding ................");

        System.out.println("...............place api ................");
        SearchResult searchResult = map.searchAddress("北京市", "海淀区宝盛广场");
        System.out.println(JSONObject.toJSON(searchResult));
        System.out.println("................end place api ................");

        System.out.println("...............parseAddress api ................");
        Coordinate coordinate = map.parseAddress("海淀区宝盛广场", "北京市", "海淀区");
        System.out.println(JSONObject.toJSON(coordinate));
        System.out.println("................end parseAddress api ................");


        System.out.println("...............ip api ................");
        IpLocationResult ipLocationResult = map.parseCityByIp("223.72.90.44");
        System.out.println(JSONObject.toJSON(ipLocationResult));
        System.out.println("................end ip api ................");

        System.out.println("...............geo reverse api ................");
        GeoReverseLocationResult geoReverseLocationResult = map.parseLocation("22.546054", "114.025974");
        System.out.println(JSONObject.toJSON(geoReverseLocationResult));
        System.out.println("................end geo reverse api ................");

        System.out.println("...............calcDistance api ................");
        RouteResult routeResult = map.calcDistance("百度大厦", "天安门");
        System.out.println(JSONObject.toJSON(routeResult));
        System.out.println("................end calcDistance api ................");
    }
}
