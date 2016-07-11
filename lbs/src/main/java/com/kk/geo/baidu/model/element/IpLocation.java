package com.kk.geo.baidu.model.element;

/**
 * 根据Ip搜索地址 返回结果
 */
public class IpLocation {
    private String address; // 简要地址
    IpAddressDetail address_detail; //详细地址信息

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public IpAddressDetail getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(IpAddressDetail address_detail) {
        this.address_detail = address_detail;
    }
}
