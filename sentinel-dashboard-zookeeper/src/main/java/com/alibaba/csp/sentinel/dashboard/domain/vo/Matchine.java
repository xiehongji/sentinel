package com.alibaba.csp.sentinel.dashboard.domain.vo;

public class Matchine {
    private String app = "";
    private Boolean  webBoolean=true;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public Boolean getWebBoolean() {
        return webBoolean;
    }

    public void setWebBoolean(Boolean webBoolean) {
        this.webBoolean = webBoolean;
    }
}
