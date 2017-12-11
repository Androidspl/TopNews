package com.topnews.adapter;

import android.content.Context;
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
import com.topnews.callbacks.TouchInterface;

import java.util.Collections;
import java.util.List;

import static android.R.id.list;
import static com.topnews.utils.Utils.leftStepList;
import static com.topnews.utils.Utils.reverseList;
import static com.topnews.utils.Utils.rightStepList;

/**
 * Created by pengleiShen on 2017/12/8.
 */

public class ItemTouchChAdapter extends RecyclerView.Adapter<MyChViewHolder> implements TouchInterface {

    private Context context;
    //是否显示delete
    public boolean isShow;

    public List<DataBean> getList() {
        return list;
    }

    public void setList(List<DataBean> list) {
        this.list = list;
    }

    private List<DataBean> list;
    private List<DataBean> recommendList;

    public ItemTouchChAdapter(Context context, List<DataBean> channleList, List<DataBean> recommendList) {
        this.context = context;
        this.list = channleList;
        this.recommendList = recommendList;
    }

    @Override
    public MyChViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyChViewHolder viewHolder = new MyChViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyChViewHolder holder, final int position) {

        holder.tv_des.setText(list.get(position).name);
        holder.tv_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DataBean bean = list.get(position);
                DataBean bean = list.remove(position);
                // 取出推荐列表添加频道前0位置的数据
//                DataBean bean_current_zero = recommendList.get(0);
                // 把取出的数据放到
//                DataBean bean_1 = new DataBean("段子", 0, "url");
                Log.e("Size:",recommendList.size() + "");
                recommendList.add(bean);
                Log.e("Size-add:",recommendList.size() + "");
//                reverseList(recommendList.size(),0,recommendList);
//                Collections.swap(recommendList, recommendList.size() , 0);
//                Collections.swap(recommendList, 2 , 1);
//                List<DataBean> subList = recommendList.subList(recommendList.size(), 0);
//                List<DataBean> subList = recommendList.subList(2, 1);
                //向左移一位
//                leftStepList(0, subList);

                notifyDataSetChanged();
                Toast.makeText(context,"删除了"+bean.name+"频道",Toast.LENGTH_SHORT).show();
                for (int i=0; i<list.size(); i++){
//                    System.out.println("第" + i + "个：" + list.get(i).toString());
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

        return list.size();
    }

    @Override
    public void onMove(int currentPosition, int targetPosition) {

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
//            System.out.println("第" + i + "个：" + list.get(i));
        }
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