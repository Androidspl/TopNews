package com.topnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.topnews.R;
import com.topnews.bean.DataBean;
import com.topnews.callbacks.NotifyInterface;
import com.topnews.callbacks.TouchInterface;

import java.util.Collections;
import java.util.List;

import static android.R.id.list;
import static com.topnews.utils.Utils.leftStepList;
import static com.topnews.utils.Utils.rightStepList;

/**
 * Created by pengleiShen on 2017/12/8.
 */

public class ItemTouchReAdapter extends RecyclerView.Adapter<MyReViewHolder> implements TouchInterface, NotifyInterface {

    private NotifyInterface notifyInterface;
    private Context context;
    //是否显示delete
    public boolean isShow;

    public List<DataBean> getList() {
        return recommendList;
    }

    public void setList(List<DataBean> list) {
        this.recommendList = list;
    }

    private List<DataBean> recommendList;
    private List<DataBean> channleList;

    public ItemTouchReAdapter(Context context, List<DataBean> channleList, List<DataBean> recommendList) {
        this.context = context;
        this.recommendList = recommendList;
        this.channleList = channleList;
    }

    @Override
    public MyReViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyReViewHolder viewHolder = new MyReViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyReViewHolder holder, final int position) {

        holder.tv_des.setText(recommendList.get(position).getName());
        holder.tv_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBean bean = recommendList.remove(position);
                channleList.add(bean);
                notifyInterface.notifyView();
                notifyDataSetChanged();
                Toast.makeText(context,"删除了"+bean.getName()+"频道",Toast.LENGTH_SHORT).show();
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
        return recommendList.size();
    }

    @Override
    public void onMove(int currentPosition, int targetPosition, List<DataBean> list) {

        Collections.swap(list, currentPosition, targetPosition);
        if (targetPosition < currentPosition) {
            List<DataBean> subList = list.subList(targetPosition + 1, currentPosition + 1);
            //向右移一位
            rightStepList(0, subList);
        } else {
            List<DataBean> subList = list.subList(currentPosition, targetPosition);
            //向左移一位
            leftStepList(0, subList);
        }
        notifyItemMoved(currentPosition, targetPosition);
    }

    @Override
    public List<DataBean> getDataBeanList() {
        return recommendList;
    }

    @Override
    public void notifyView() {
        onMove(recommendList.size() - 1, 0, recommendList);
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

class MyReViewHolder extends RecyclerView.ViewHolder {

    public ImageView iv_icon;
    public TextView tv_des;

    public MyReViewHolder(View itemView) {
        super(itemView);
        iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        tv_des = (TextView) itemView.findViewById(R.id.tv_des);
    }
}
