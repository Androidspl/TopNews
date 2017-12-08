package com.topnews.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.topnews.R;
import com.topnews.adapter.ItemTouchAdapter;
import com.topnews.bean.DataBean;
import com.topnews.custom.CustomItemTouchCallBack;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pengleiShen on 2017/11/29.
 */

public class ChannelActivity extends FragmentActivity {

    private RecyclerView rv;
    public List<DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        initData();
        initView();
    }

    private void initView() {

        rv = (RecyclerView) findViewById(R.id.rl_view);
        rv.setLayoutManager(new GridLayoutManager(this, 4));
        ItemTouchAdapter adapter = new ItemTouchAdapter(this, list);
        rv.setAdapter(adapter);
        //关联ItemTouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(new CustomItemTouchCallBack(adapter));
        touchHelper.attachToRecyclerView(rv);

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

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
        list.add(bean7);
        list.add(bean8);
        list.add(bean9);
    }
}
