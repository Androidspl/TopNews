package com.topnews;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.topnews.activity.ChannelActivity;
import com.topnews.adapter.NewsFragmentPagerAdapter;
import com.topnews.bean.NewsClassify;
import com.topnews.custom.CustomTabView;
import com.topnews.fragment.NewsFragment;
import com.topnews.tool.BaseTools;
import com.topnews.tool.Constants;
import com.topnews.utils.Utils;
import com.topnews.view.ColumnHorizontalScrollView;
import com.topnews.view.DrawerView;

import java.util.ArrayList;
/**
 * （android高仿系列）今日头条 --新闻阅读器
 * author:RA
 * blog : http://blog.csdn.net/vipzjyno1/
 */
public class MainActivity extends FragmentActivity implements CustomTabView.OnTabSelectListener{
    /** 自定义HorizontalScrollView */
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    LinearLayout mRadioGroup_content;
    LinearLayout ll_more_columns;
    RelativeLayout rl_column;
    private ViewPager mViewPager;
    private ImageView button_more_columns;
    /** 新闻分类列表*/
    private ArrayList<NewsClassify> newsClassify=new ArrayList<NewsClassify>();
    /** 当前选中的栏目*/
    private int columnSelectIndex = 0;
    /** 左阴影部分*/
    public ImageView shade_left;
    /** 右阴影部分 */
    public ImageView shade_right;
    /** 屏幕宽度 */
    private int mScreenWidth = 0;
    /** Item宽度 */
    private int mItemWidth = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    protected SlidingMenu side_drawer;

    /** head 头部 的中间的loading*/
    private ProgressBar top_progress;
    /** head 头部 中间的刷新按钮*/
    private ImageView top_refresh;
    /** head 头部 的左侧菜单 按钮*/
    private ImageView top_head;
    /** head 头部 的右侧菜单 按钮*/
    private ImageView top_more;
    private CustomTabView custom_tab_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mScreenWidth = BaseTools.getWindowsWidth(this);
        mItemWidth = mScreenWidth / 7;// 一个Item宽度为屏幕的1/7
        initView();
        //初始化底部tab
        initTabView();
        initSlidingMenu();
    }

    private void initTabView() {
        // 首页
        CustomTabView.Tab   tabHome = new CustomTabView.Tab()
                .setmText("首页")
                .setmIconNormalResId(R.drawable.home)
                .setmIconPressResId(R.drawable.home_select)
                .setmNormalColor(getResources().getColor(R.color.gray))
                .setmSelectColor(getResources().getColor(R.color.red))
                .setImageViewWidht(Utils.dip2px(getApplicationContext(), 23))
                .setImageViewHeight(Utils.dip2px(getApplicationContext(), 23));
        custom_tab_view.addTab(tabHome);

        // 视频
        CustomTabView.Tab tabDis = new CustomTabView.Tab()
                .setmText("西瓜视频")
                .setmIconNormalResId(R.drawable.video)
                .setmIconPressResId(R.drawable.video_select)
                .setmNormalColor(getResources().getColor(R.color.gray))
                .setmSelectColor(getResources().getColor(R.color.red))
                .setImageViewWidht(Utils.dip2px(getApplicationContext(), 23))
                .setImageViewHeight(Utils.dip2px(getApplicationContext(), 23));
        custom_tab_view.addTab(tabDis);

        // 凸出的加号
//        CustomTabView.Tab tabAdd = new CustomTabView.Tab()
////                .setmText("")
//                .setmIconNormalResId(R.drawable.add_normal)
//                .setmIconPressResId(R.drawable.add_select)
//                .setmNormalColor(R.color.mTabTv)
//                .setmSelectColor(Color.BLACK)
//                .setImageViewWidht(Utils.dip2px(getApplicationContext(), 48))
//                .setImageViewHeight(Utils.dip2px(getApplicationContext(), 48));
//        custom_tab_view.addTab(tabAdd);

        // 微头条
        CustomTabView.Tab tabMes = new CustomTabView.Tab()
                .setmText("微头条")
                .setmIconNormalResId(R.drawable.topnews)
                .setmIconPressResId(R.drawable.topnews_select)
                .setmNormalColor(getResources().getColor(R.color.gray))
                .setmSelectColor(getResources().getColor(R.color.red))
                .setImageViewWidht(Utils.dip2px(getApplicationContext(), 23))
                .setImageViewHeight(Utils.dip2px(getApplicationContext(), 23));
        custom_tab_view.addTab(tabMes);

        // 我的
        CustomTabView.Tab tabMy = new CustomTabView.Tab()
                .setmText("我的")
                .setmIconNormalResId(R.drawable.my)
                .setmIconPressResId(R.drawable.my_select)
                .setmNormalColor(getResources().getColor(R.color.gray))
                .setmSelectColor(getResources().getColor(R.color.red))
                .setImageViewWidht(Utils.dip2px(getApplicationContext(), 23))
                .setImageViewHeight(Utils.dip2px(getApplicationContext(), 23));
        custom_tab_view.addTab(tabMy);

        //设置默认选中首页
        custom_tab_view.setCurrentItem(0);

        custom_tab_view.setOnTabSelectListener(this);
    }

    /** 初始化layout控件*/
    private void initView() {
        mColumnHorizontalScrollView =  (ColumnHorizontalScrollView)findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) findViewById(R.id.rl_column);
        button_more_columns = (ImageView) findViewById(R.id.button_more_columns);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        shade_left = (ImageView) findViewById(R.id.shade_left);
        shade_right = (ImageView) findViewById(R.id.shade_right);
        top_head = (ImageView) findViewById(R.id.top_head);
        top_more = (ImageView) findViewById(R.id.top_more);
        top_refresh = (ImageView) findViewById(R.id.top_refresh);
        top_progress = (ProgressBar) findViewById(R.id.top_progress);
        custom_tab_view = (CustomTabView) findViewById(R.id.custom_tab_view);
        button_more_columns.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, ChannelActivity.class);
                startActivity(intent);
            }
        });
        top_head.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(side_drawer.isMenuShowing()){
                    side_drawer.showContent();
                }else{
                    side_drawer.showMenu();
                }
            }
        });
        top_more.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(side_drawer.isSecondaryMenuShowing()){
                    side_drawer.showContent();
                }else{
                    side_drawer.showSecondaryMenu();
                }
            }
        });
        setChangelView();
    }
    /**
     *  当栏目项发生变化时候调用
     * */
    private void setChangelView() {
        initColumnData();
        initTabColumn();
        initFragment();
    }
    /** 获取Column栏目 数据*/
    private void initColumnData() {
        newsClassify = Constants.getData();
    }

    /**
     *  初始化Column栏目项
     * */
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count =  newsClassify.size();
        mColumnHorizontalScrollView.setParam(this, mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
        for(int i = 0; i< count; i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth , LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
//			TextView localTextView = (TextView) mInflater.inflate(R.layout.column_radio_item, null);
            TextView columnTextView = new TextView(this);
            columnTextView.setTextAppearance(this, R.style.top_category_scroll_view_item_text);
//			localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
            columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(newsClassify.get(i).getTitle());
            columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if(columnSelectIndex == i){
                columnTextView.setSelected(true);
            }
            columnTextView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else{
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                    Toast.makeText(getApplicationContext(), newsClassify.get(v.getId()).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(columnTextView, i ,params);
        }
    }
    /**
     *  选择的Column里面的Tab
     * */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
            // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
            // mItemWidth , 0);
        }
        //判断是否选中
        for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }
    /**
     *  初始化Fragment
     * */
    private void initFragment() {
        int count =  newsClassify.size();
        for(int i = 0; i< count;i++){
            Bundle data = new Bundle();
            data.putString("text", newsClassify.get(i).getTitle());
            NewsFragment newfragment = new NewsFragment();
            newfragment.setArguments(data);
            fragments.add(newfragment);
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
//		mViewPager.setOffscreenPageLimit(0);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.setOnPageChangeListener(pageListener);
    }
    /**
     *  ViewPager切换监听方法
     * */
    public OnPageChangeListener pageListener= new OnPageChangeListener(){

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    protected void initSlidingMenu() {
        side_drawer = new DrawerView(this).initSlidingMenu();
    }

    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(side_drawer.isMenuShowing() ||side_drawer.isSecondaryMenuShowing()){
                side_drawer.showContent();
            }else {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(this, "在按一次退出",
                            Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
            return true;
        }
        //拦截MENU按钮点击事件，让他无任何操作
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTabSelected(int position, View view) {

    }
}
