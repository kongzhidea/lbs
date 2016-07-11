package com.kk.geo.baidu.model;

import com.kk.geo.baidu.model.element.IpLocation;

/**
 * 根据Ip搜索地址 返回结果
 */
public class IpLocationResult extends Result {
    private IpLocation content; //  地址 CN|吉林|长春|None|CERNET|0|0
    private String address; //详细内容

    public IpLocation getContent() {
        return content;
    }

    public void setContent(IpLocation content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
