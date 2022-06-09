package com.example.finalwebprojectepam.controller;

public class Router {
    public enum RouterType {
        FORWARD,
        REDIRECT
    }

    private String pagePath;
    private RouterType routerType;

    public Router(String pagePath) {
        this.pagePath = pagePath;
        this.routerType = RouterType.FORWARD;
    }

    public Router() {
        this.routerType = RouterType.FORWARD;
    }

    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public void setRouterType(RouterType routerType) {
        this.routerType = routerType;
    }
}