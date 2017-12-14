package com.topnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.topnews.R;

import java.util.List;

/**
 * Created by pengleiShen on 2017/11/14.
 */

public class PopWindowChannelAdapter extends BaseAdapter {

    private List<String> mList;
    private Context context;

    public PopWindowChannelAdapter(Context context, List<String> list){
        this.context = context;
        this.mList = list;
    };

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.activity_channel, null);
//            viewHolder.pop_text = (TextView) view.findViewById(R.id.pop_text);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

//        viewHolder.pop_text.setText(mList.get(position));
        return view;
    }

    class ViewHolder{
        TextView pop_text;
    }
}
