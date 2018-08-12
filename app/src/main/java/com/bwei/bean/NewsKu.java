package com.bwei.bean;

public class NewsKu {

    public Integer id;
    public String url;
    public String json;

    public NewsKu(String url, String json) {
        this.url = url;
        this.json = json;
    }

    public NewsKu() {
    }
}
