package com.topnews.callbacks;

import com.topnews.bean.DataBean;

import java.util.List;

/**
 * Created by zhoukai on 2017/7/14.
 */

public interface TouchInterface {

    void onMove(int currentPosition, int targetPosition, List<DataBean> list);

    List<DataBean> getDataBeanList();
}
