package com.topnews.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoukai on 2017/7/3.
 */

public class DataBean implements Serializable{

    private List<DataBean> tab;
    private String name;
    private int page;
    private String url;

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
