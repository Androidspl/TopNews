package com.topnews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.topnews.MainActivity;
import com.topnews.R;
import com.topnews.adapter.NewsAdapter;
import com.topnews.adapter.NewsFragmentPagerAdapter;
import com.topnews.bean.DataBean;

import java.util.List;

import static android.R.attr.key;


/**
 * Created by pengleiShen on 2017/10/25.
 */

public class HotTopicFragment extends Fragment {

    private TextView hottopic_tv;
    private String tab_name;
    private View view;
    public final static int SET_NEWSLIST = 0;

    private NewsAdapter mAdapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case SET_NEWSLIST:
                    if (channlelsit!=null && channlelsit.size()>0){
                        hottopic_tv.setText(channlelsit.get(columnSelectIndex).getName());
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private ListView mListView;
    private int columnSelectIndex;
    private List<DataBean> channlelsit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    public void initView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hottopic,null);
        hottopic_tv = (TextView) view.findViewById(R.id.hottopic_tv);
        hottopic_tv.setText(tab_name);
        hottopic_tv.setTag(tab_name);
//        hottopic_tv.setTag("fragment_name");

    }

    public void setHotText(String text){
        tab_name = text;
    }

    /** 此方法意思为fragment是否可见 ,可见时候加载数据 */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
//            MainActivity mainActivity = new MainActivity();
//            columnSelectIndex = mainActivity.columnSelectIndex;
//            channlelsit = mainActivity.channleList;
//            handler.obtainMessage(SET_NEWSLIST).sendToTarget();
            //fragment可见时加载数据
//            if(tab_name !=null && tab_name.length() !=0){
//                handler.obtainMessage(SET_NEWSLIST).sendToTarget();
//            }else{
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        try {
//                            Thread.sleep(2);
//                        } catch (InterruptedException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                        handler.obtainMessage(SET_NEWSLIST).sendToTarget();
//                    }
//                }).start();
//            }
        }else{
            //fragment不可见时不执行操作
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

}
