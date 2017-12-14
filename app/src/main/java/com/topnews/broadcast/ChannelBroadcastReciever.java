package com.topnews.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.topnews.bean.DataBean;

import java.util.List;

/**
 * Created by pengleiShen on 2017/12/13.
 */

public class ChannelBroadcastReciever extends BroadcastReceiver {

    public List<DataBean> mTabsText;

    @Override
    public void onReceive(Context context, Intent intent) {
        // 收到消息时候调用的方法
        mTabsText = (List<DataBean>) intent.getSerializableExtra("channel");
    }
}
