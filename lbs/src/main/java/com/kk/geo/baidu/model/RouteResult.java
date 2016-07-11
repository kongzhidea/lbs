package com.kk.geo.baidu.model;

public class RouteResult extends Result {
    private String message;
    private RouteElementsResult result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RouteElementsResult getResult() {
        return result;
    }

    public void setResult(RouteElementsResult result) {
        this.result = result;
    }
}

