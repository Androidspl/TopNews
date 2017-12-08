package com.topnews.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.sql.Time;

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

}
