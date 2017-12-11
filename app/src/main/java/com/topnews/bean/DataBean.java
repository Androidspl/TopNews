package com.topnews.bean;

/**
 * Created by zhoukai on 2017/7/3.
 */

public class DataBean {
    public String name;
    int page;
    String url;

    public DataBean(String name, int page, String url) {
        this.name = name;
        this.page = page;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    @Override
//    public String toString() {
//        return name;
//    }


    @Override
    public String toString() {
        return "DataBean{" +
                "name='" + name + '\'' +
                ", page=" + page +
                ", url='" + url + '\'' +
                '}';
    }
}
