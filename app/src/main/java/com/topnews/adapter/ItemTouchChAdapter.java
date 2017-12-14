package com.topnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.topnews.R;
import com.topnews.bean.DataBean;
import com.topnews.callbacks.NotifyInterface;
import com.topnews.callbacks.TouchInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.id.list;
import static com.topnews.utils.Utils.leftStepList;
import static com.topnews.utils.Utils.reverseList;
import static com.topnews.utils.Utils.rightStepList;

/**
 * Created by pengleiShen on 2017/12/8.
 */

public class ItemTouchChAdapter extends RecyclerView.Adapter<MyChViewHolder> implements TouchInterface, NotifyInterface {

    private Context context;
    //是否显示delete
    public boolean isShow;
    private List<DataBean> channelList;
    private List<DataBean> recommendList;
    private NotifyInterface notifyInterface;

    public List<DataBean> getList() {
        return channelList;
    }

    public void setList(List<DataBean> list) {
        this.channelList = list;
    }

    public ItemTouchChAdapter(Context context, List<DataBean> channleList, List<DataBean> recommendList) {
        this.context = context;
        this.channelList = channleList;
        this.recommendList = recommendList;
    }



    @Override
    public MyChViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyChViewHolder viewHolder = new MyChViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyChViewHolder holder, final int position) {

        holder.tv_des.setText(channelList.get(position).getName());
        holder.tv_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position > 2){
                    DataBean bean = channelList.remove(position);
                    recommendList.add(bean);
                    notifyInterface.notifyView();
                    notifyDataSetChanged();
                    Toast.makeText(context,"删除了"+bean.getName()+"频道",Toast.LENGTH_SHORT).show();

                }
            }
        });

        holder.tv_des.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isShow = true;
                notifyDataSetChanged();
                return true;
            }
        });

        if (isShow) {
            holder.iv_icon.setVisibility(View.VISIBLE);
        } else {
            holder.iv_icon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {

        return channelList.size();
    }

    @Override
    public void onMove(int currentPosition, int targetPosition, List<DataBean> list) {

        Collections.swap(list, currentPosition, targetPosition);
//        Log.e("currentPosition:",currentPosition+"");
//        Log.e("targetPosition:",targetPosition+"");
        if (targetPosition < currentPosition) {
            List<DataBean> subList = list.subList(targetPosition + 1, currentPosition + 1);
            Log.e("currentPosition:",currentPosition+"");
            Log.e("targetPosition:",targetPosition+"");
            //向右移一位
            rightStepList(0, subList);
        } else {
            List<DataBean> subList = list.subList(currentPosition, targetPosition);
            //向左移一位
            leftStepList(0, subList);
        }
        notifyItemMoved(currentPosition, targetPosition);
        for (int i=0; i<list.size(); i++){
//            System.out.println("第" + i + "个：" + list.get(i).toString());
            System.out.println("第" + i + "个：" + list.get(i));
        }
    }

    @Override
    public List<DataBean> getDataBeanList() {
        return channelList;
    }

    @Override
    public void notifyView() {
        notifyDataSetChanged();
    }

    /**
     * @Description:刷新界面
     *
     * @param
     *
     * @return
     *
     */
    public void setNotifyInterface(NotifyInterface notifyInterface){
        this.notifyInterface = notifyInterface;
    }

}

class MyChViewHolder extends RecyclerView.ViewHolder {

    public ImageView iv_icon;
    public TextView tv_des;

    public MyChViewHolder(View itemView) {
        super(itemView);
        iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        tv_des = (TextView) itemView.findViewById(R.id.tv_des);
    }

}
