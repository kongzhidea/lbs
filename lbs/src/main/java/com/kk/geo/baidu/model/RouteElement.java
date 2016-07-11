package com.kk.geo.baidu.model;

public class RouteElement {
    private RouteElementItem distance; // 距离
    private RouteElementItem duration; // 时间间隔

    public RouteElementItem getDistance() {
        return distance;
    }

    public void setDistance(RouteElementItem distance) {
        this.distance = distance;
    }

    public RouteElementItem getDuration() {
        return duration;
    }

    public void setDuration(RouteElementItem duration) {
        this.duration = duration;
    }
}
