package com.topnews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topnews.R;


/**
 * Created by pengleiShen on 2017/10/25.
 */

public class HotTopicFragment extends Fragment {

    private TextView hottopic_tv;
    private String tab_name;
    private View view;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
//        context = getActivity();
        initView();
        return view;
    }

    public void initView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hottopic,null);
        hottopic_tv = (TextView) view.findViewById(R.id.hottopic_tv);
        hottopic_tv.setText(tab_name);
//        hottopic_tv.setText("热点");
    }

    public void setHotText(String text){
        tab_name = text;
//        hottopic_tv.setText(tab_name);
//        if (hottopic_tv != null) {
//            hottopic_tv.setText(tab_name);
//        }
    }

}
