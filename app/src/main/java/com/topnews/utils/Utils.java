package com.topnews.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

/**
 * Created by pengleiShen on 2017/10/19.
 */

public class Utils {

    /**
     * @Description:通过开启子线程的方式，设计延时操作。
     *
     * @param
     *
     * @return
     *
     */
    public static void  msg_Delayed(final Handler handler, final Message msg, final long time) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(msg);
            }
        }).start();

    }

    /**
     * @Description: 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param
     *
     * @return
     *
     */
    public static int dip2px (Context context, float dpValue){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * @Description: 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param
     *
     * @return
     *
     */
    public static int px2dip (Context context, float pxValue){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }

    /**
     * 利用反转的思想对数据进行排序
     * 例如：list{0,1,2,3,4,5,6,7} 左移一位
     * 第一步:第一位先反转{0,1,2,3,4,5,6,7}
     * 第二部:剩下的在反转{0,7,6,5,4,3,2,1}
     * 第三步:全部反转{1,2,3,4,5,6,7,0}
     *
     *  例如：list{0,1,2,3,4,5,6,7} 右移一位
     * 第一步:最右边一位先反转{1,2,3,4,5,6,7}
     * 第二部:剩下的在反转{6,5,4,3,2,1,0,7}
     * 第三步:全部反转{7,6,5,4,3,2,1,0}
     *
     * 因为list的index是从0开始的，step要相应的-1
     * 优点:少创建对象，优化内存
     *
     * @param start
     * @param end
     * @param list
     */

    public static  void reverseList(int start,int end,List list){

        int count = (end+1-start)/2 ;
        for(int i = 0;i< count;i++){
            Object temp = list.get(start+i);
            list.set(start+i,list.get(end-i));
            list.set(end-i,temp);
        }
    }
    public static void leftStepList(int step,List list){

        int size = list.size() -1;
        //左移
        reverseList(0,step,list);
        reverseList(step+1,size,list);
        reverseList(0,size,list);

    }

    public static void rightStepList(int step,List list){

        int size = list.size() -1;
        //右移
        reverseList(size-step,size,list);
        reverseList(0,size-step-1,list);
        reverseList(0,size,list);
    }

    /**
     * @Description:
     *
     * @param
     *
     * @return
     *
     */
    // 构建一个隐式意图，然后发送广播。
    public static void sendBroadCast (Context context, Serializable serializable){
        Intent intent = new Intent("com.topnews.adapter.channel");
        Bundle bundle = new Bundle();
//        bundle.putSerializable("channel", (Serializable) channelList);
        bundle.putSerializable("channel", serializable);
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
    }

}
