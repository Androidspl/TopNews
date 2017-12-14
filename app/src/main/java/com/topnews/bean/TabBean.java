package com.topnews.bean;

import java.io.Serializable;
import java.util.List;

import static android.R.attr.name;

/**
 * Created by zhoukai on 2017/7/3.
 */

public class TabBean implements Serializable{

    private List<TabBean> tab;

    public TabBean(List<TabBean> tab) {
        this.tab = tab;
    }

}
