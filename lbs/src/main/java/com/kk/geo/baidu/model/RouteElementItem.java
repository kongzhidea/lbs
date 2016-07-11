package com.kk.geo.baidu.model;

public class RouteElementItem {
    private String text; // 如  22.6公里，  1.4小时
    private int value; // 如  22626(米)，       4948(秒)

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
