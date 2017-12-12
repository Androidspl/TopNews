package com.topnews.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.topnews.R;
import com.topnews.adapter.ItemTouchChAdapter;
import com.topnews.adapter.ItemTouchReAdapter;
import com.topnews.bean.DataBean;
import com.topnews.callbacks.NotifyInterface;
import com.topnews.custom.CustomItemTouchCallBack;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pengleiShen on 2017/11/29.
 */

public class ChannelActivity extends FragmentActivity {

    private RecyclerView rv;
//    public List<DataBean> list = new ArrayList<>();
//    public List<DataBean> list_recommend = new ArrayList<>();
    public List<DataBean> channleList = new ArrayList<>();
    public List<DataBean> recommendList = new ArrayList<>();
    private RecyclerView rl_recommend;
    private ItemTouchChAdapter adapter_channel;
    private ItemTouchReAdapter adapter_recommend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        initData();
        initView();
    }

    private void initView() {

        //我的频道
        rv = (RecyclerView) findViewById(R.id.rl_view);
        rv.setLayoutManager(new GridLayoutManager(this, 4));
        adapter_channel = new ItemTouchChAdapter(this, channleList, recommendList);
        rv.setAdapter(adapter_channel);
        //频道推荐
        rl_recommend = (RecyclerView) findViewById(R.id.rl_recommend);
        rl_recommend.setLayoutManager(new GridLayoutManager(this, 4));
        adapter_recommend = new ItemTouchReAdapter(this, channleList, recommendList);
        rl_recommend.setAdapter(adapter_recommend);

        adapter_channel.setNotifyInterface(adapter_recommend);
        adapter_recommend.setNotifyInterface(adapter_channel);

        //关联ItemTouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(new CustomItemTouchCallBack(adapter_channel));
        touchHelper.attachToRecyclerView(rv);
//        ItemTouchHelper touchHelper_recommend = new ItemTouchHelper(new CustomItemTouchCallBack(adapter));
//        touchHelper_recommend.attachToRecyclerView(rv);

    }

    private void initData() {

        DataBean bean1 = new DataBean("体育", 0, "url");
        DataBean bean2 = new DataBean("新闻", 1, "url");
        DataBean bean3 = new DataBean("影视", 2, "url");
        DataBean bean4 = new DataBean("电视剧", 3, "url");
        DataBean bean5 = new DataBean("热点", 4, "url");
        DataBean bean6 = new DataBean("推荐", 5, "url");
        DataBean bean7 = new DataBean("屌丝男士", 6, "url");
        DataBean bean8 = new DataBean("音乐", 7, "url");
        DataBean bean9 = new DataBean("电影", 8, "url");

//        list.add(bean1);
//        list.add(bean2);
//        list.add(bean3);
//        list.add(bean4);
//        list.add(bean5);
//        list.add(bean6);
//        list.add(bean7);
//        list.add(bean8);
//        list.add(bean9);

        channleList.add(bean1);
        channleList.add(bean2);
        channleList.add(bean3);
        channleList.add(bean4);
        channleList.add(bean5);
        channleList.add(bean6);
        channleList.add(bean7);
        channleList.add(bean8);
        channleList.add(bean9);

        DataBean bean_1 = new DataBean("段子", 0, "url");
        DataBean bean_2 = new DataBean("育儿", 1, "url");
        DataBean bean_3 = new DataBean("北京", 2, "url");
        DataBean bean_4 = new DataBean("新时代", 3, "url");
        DataBean bean_5 = new DataBean("社会", 4, "url");
        DataBean bean_6 = new DataBean("正能量", 5, "url");
        DataBean bean_7 = new DataBean("房产", 6, "url");
        DataBean bean_8 = new DataBean("历史", 7, "url");
        DataBean bean_9 = new DataBean("搞笑", 8, "url");

//        list_recommend.add(bean_1);
//        list_recommend.add(bean_2);
//        list_recommend.add(bean_3);
//        list_recommend.add(bean_4);
//        list_recommend.add(bean_5);
//        list_recommend.add(bean_6);
//        list_recommend.add(bean_7);
//        list_recommend.add(bean_8);
//        list_recommend.add(bean_9);

        recommendList.add(bean_1);
        recommendList.add(bean_2);
        recommendList.add(bean_3);
        recommendList.add(bean_4);
        recommendList.add(bean_5);
        recommendList.add(bean_6);
        recommendList.add(bean_7);
        recommendList.add(bean_8);
        recommendList.add(bean_9);

    }
}
