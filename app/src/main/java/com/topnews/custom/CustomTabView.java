package com.topnews.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.topnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengleiShen on 2017/10/20.
 */

public class CustomTabView extends LinearLayout implements View.OnClickListener{

    private List<View> mTabViews ;//设置Tab的文字
    private List<Tab> mTabs;
    private TextView custom_tab_text;
    private ImageView custom_tab_iv;
    private OnTabSelectListener mOnTabSelectListener;



    public void setOnTabSelectListener(OnTabSelectListener onTabSelectListener){
        mOnTabSelectListener = onTabSelectListener;
    };

    public CustomTabView(Context context) {
        super(context);
        init();
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTabView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }



    private void init() {
        //水平
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        mTabViews = new ArrayList<>();
        mTabs = new ArrayList<Tab>();
    }

    /**
     * @Description:添加T
     *
     * @param
     *
     * @return
     *
     */
    public void addTab(Tab tab){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_bottom_tab,null);
        custom_tab_text = (TextView) view.findViewById(R.id.custom_tab_text);
        custom_tab_iv = (ImageView) view.findViewById(R.id.custom_tab_iv);
        //设置params
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = tab.mHeight;
        params.width = tab.mWidth;

        // 控制中间的凸出按钮的文字不显示
//        if (mTabViews.size() == 2) {
//            custom_tab_text.setVisibility(View.GONE);
//            params.setMargins(0, 0, 0, 0);
////            params.gravity = Gravity.CENTER;
//        } else {
//            custom_tab_text.setVisibility(View.VISIBLE);
//        }

        custom_tab_text.setVisibility(View.VISIBLE);

        custom_tab_iv.setLayoutParams(params);

        //设置Tab图标、文字、颜色
        custom_tab_text.setText(tab.mText);
        custom_tab_iv.setImageResource(tab.mIconNormalResId );

        mTabViews.add(view);
        //设置按钮tag
        view.setTag(mTabViews.size() - 1);
        mTabs.add(tab);

        //记录加入了几个view
        addView(view);

        view.setOnClickListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //调整每个Tab的大小
        for (int i = 0; i < mTabViews.size(); i++) {
            View view = mTabViews.get(i);
            int width = getResources().getDisplayMetrics().widthPixels / (mTabViews.size());
            LayoutParams params = new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
        }
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        if (mOnTabSelectListener != null){
            mOnTabSelectListener.onTabSelected(position,view);
        }
        updateState(position);
    }

    private void updateState(int position) {
        for (int i=0;i<mTabViews.size();i++) {
            View view = mTabViews.get(i);
            ImageView custom_tab_iv = (ImageView) view.findViewById(R.id.custom_tab_iv);
            TextView custom_tab_text = (TextView) view.findViewById(R.id.custom_tab_text);
            if (i == position) {
                custom_tab_iv.setImageResource(mTabs.get(i).mIconPressResId);
                custom_tab_text.setText(mTabs.get(i).mText);
                custom_tab_text.setTextColor(mTabs.get(i).mSelectColor);
            } else {
                custom_tab_iv.setImageResource(mTabs.get(i).mIconNormalResId);
                custom_tab_text.setText(mTabs.get(i).mText);
                custom_tab_text.setTextColor(mTabs.get(i).mNormalColor);
            }
        }

    }

    public void setCurrentItem(int position) {
        updateState(position);
    }

    public interface OnTabSelectListener{
        public void onTabSelected(int position, View view);
    }

    /**
     * @Description:自定义的Tab泛型
     *
     * @param
     *
     * @return
     *
     */
    public static class Tab{

        //Icon默认的数，和点击的数
        private int mIconNormalResId;
        private int mIconPressResId;

        //字体默认颜色和选中颜色
        private int mNormalColor;
        private int mSelectColor;

        //设置icon对应文本文字
        private String mText;

        private int mHeight;
        private int mWidth;

        //Margins
        private int left;
        private int right;
        private int top;
        private int mBottom;

        public Tab setmIconNormalResId(int mIconNormalResId) {
            this.mIconNormalResId = mIconNormalResId;
            return this;
        }

        public Tab setmIconPressResId(int mIconPressResId) {
            this.mIconPressResId = mIconPressResId;
            return this;
        }

        public Tab setmNormalColor(int mNormalColor) {
            this.mNormalColor = mNormalColor;
            return this;
        }

        public Tab setmSelectColor(int mSelectColor) {
            this.mSelectColor = mSelectColor;
            return this;
        }

        public Tab setmText(String mText) {
            this.mText = mText;
            return this;
        }

        public Tab setImageViewHeight(int mHeight) {
            this.mHeight = mHeight;
            return this;
        }

        public int getImageViewHeight() {
            return mHeight;
        }

        public Tab setImageViewWidht(int mWidth) {
            this.mWidth = mWidth;
            return this;
        }

        public int getImageViewWidth() {
            return mWidth;
        }

        public Tab setImageViewMargins(int left, int right, int top, int bottom) {
            this.left = left;
            this.right = right;
            this.top = top;
            this.mBottom = bottom;
            return this;
        }

        public int getImageViewMarginBottom() {
            return mBottom;
        }
    }
}
