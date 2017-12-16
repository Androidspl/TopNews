package com.topnews;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.topnews.adapter.ItemTouchChAdapter;
import com.topnews.adapter.ItemTouchReAdapter;
import com.topnews.adapter.NewsFragmentPagerAdapter;
import com.topnews.bean.DataBean;
import com.topnews.bean.NewsClassify;
import com.topnews.broadcast.ChannelBroadcastReciever;
import com.topnews.custom.CustomItemTouchCallBack;
import com.topnews.custom.CustomTabView;
import com.topnews.fragment.FilmFragment;
import com.topnews.fragment.FollowFragment;
import com.topnews.fragment.HotTopicFragment;
import com.topnews.fragment.RecommendFragment;
import com.topnews.fragment.SportFragment;
import com.topnews.tool.BaseTools;
import com.topnews.tool.Constants;
import com.topnews.utils.Utils;
import com.topnews.view.ColumnHorizontalScrollView;
import com.topnews.view.DrawerView;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

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
    public int columnSelectIndex = 0;
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
    // 创建一个BroadcastReciever
    ChannelBroadcastReciever channelBroadcastReciever;
    private List<DataBean> mTabsText;
    private View mPopView;
    private PopupWindow mPopupWindow;
    private View main;
    private RecyclerView rl_channel;
    private ItemTouchChAdapter adapter_channel;
    public List<DataBean> channleList = new ArrayList<>();
    public List<DataBean> recommendList = new ArrayList<>();
    private RecyclerView rl_recommend;
    private ItemTouchReAdapter adapter_recommend;
    private NewsFragmentPagerAdapter mAdapetr;
    // 创建推荐的Fragment
    private RecommendFragment reFragment;
    // 创建关注的Fragment
    private FollowFragment foFragment;
    // 创建热点的Fragment
    private HotTopicFragment hotFragment;
    // 创建体育的Fragment
    private SportFragment spFragment;
    // 创建影视的Fragment
    private FilmFragment flFragment;

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
        //初始化广播
//        initBroadCast();
        //初始化popwindow
        initPopWindow();
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

    /**
     * @Description:初始化popwindow
     *
     * @param
     *
     * @return
     *
     */
    private void initPopWindow() {
        // 初始化popwindow
        mPopView = LayoutInflater.from(this).inflate(R.layout.activity_channel, null);
        initPopData();
        initPopView();
    }

    private void initPopView() {

        if (mPopupWindow == null){
            mPopupWindow = new PopupWindow(mPopView, ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
        ImageView close_channel = (ImageView) mPopView.findViewById(R.id.close_channel);
        close_channel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                reTabColumn();
                reFragment();

//                initFragment();
//                for (int i=0; i<channleList.size(); i++) {
//                    String tab_name = channleList.get(i).getName();
//                    switch (tab_name) {
//                        case "推荐":
//                            if (reFragment != null) {
//
//                            }
//                            break;
//
//                    }
//                }
            }
        });
        //我的频道
        rl_channel = (RecyclerView) mPopView.findViewById(R.id.rl_view);
        rl_channel.setLayoutManager(new GridLayoutManager(this, 4));
        adapter_channel = new ItemTouchChAdapter(this, channleList, recommendList);
        rl_channel.setAdapter(adapter_channel);
        //频道推荐
        rl_recommend = (RecyclerView) mPopView.findViewById(R.id.rl_recommend);
        rl_recommend.setLayoutManager(new GridLayoutManager(this, 4));
        adapter_recommend = new ItemTouchReAdapter(this, channleList, recommendList);
        rl_recommend.setAdapter(adapter_recommend);

        adapter_channel.setNotifyInterface(adapter_recommend);
        adapter_recommend.setNotifyInterface(adapter_channel);

        //关联ItemTouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(new CustomItemTouchCallBack(adapter_channel));
        touchHelper.attachToRecyclerView(rl_channel);

    }



    private void initPopData() {

        DataBean bean1 = new DataBean("推荐", 0, "url");
        DataBean bean2 = new DataBean("关注", 1, "url");
        DataBean bean3 = new DataBean("热点", 2, "url");
        DataBean bean4 = new DataBean("体育", 3, "url");
        DataBean bean5 = new DataBean("影视", 4, "url");
//        DataBean bean6 = new DataBean("娱乐", 5, "url");
//        DataBean bean7 = new DataBean("新闻", 6, "url");
//        DataBean bean8 = new DataBean("音乐", 7, "url");
//        DataBean bean9 = new DataBean("电影", 8, "url");

        channleList.add(bean1);
        channleList.add(bean2);
        channleList.add(bean3);
        channleList.add(bean4);
        channleList.add(bean5);
//        channleList.add(bean6);
//        channleList.add(bean7);
//        channleList.add(bean8);
//        channleList.add(bean9);

        DataBean bean_1 = new DataBean("段子", 0, "url");
        DataBean bean_2 = new DataBean("育儿", 1, "url");
        DataBean bean_3 = new DataBean("北京", 2, "url");
        DataBean bean_4 = new DataBean("新时代", 3, "url");
        DataBean bean_5 = new DataBean("社会", 4, "url");
        DataBean bean_6 = new DataBean("正能量", 5, "url");
        DataBean bean_7 = new DataBean("房产", 6, "url");
        DataBean bean_8 = new DataBean("历史", 7, "url");
        DataBean bean_9 = new DataBean("搞笑", 8, "url");

        recommendList.add(bean_1);
        recommendList.add(bean_2);
        recommendList.add(bean_3);
        recommendList.add(bean_4);
        recommendList.add(bean_5);
        recommendList.add(bean_6);
        recommendList.add(bean_7);
        recommendList.add(bean_8);
        recommendList.add(bean_9);

        setChangelView();
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
        main = LayoutInflater.from(this).inflate(R.layout.main,null);
        button_more_columns.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Intent intent = new Intent(MainActivity.this, ChannelActivity.class);
//                startActivity(intent);
                if (mPopupWindow != null){
//                    mPopupWindow = new PopupWindow(mPopView, ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//                    mPopupWindow.showAsDropDown();
                    mPopupWindow.showAtLocation(main,Gravity.NO_GRAVITY,0,0);
                }
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
    }
    /**
     *  当栏目项发生变化时候调用
     * */
    private void setChangelView() {
//        initColumnData();
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
//        int count =  newsClassify.size();
        int count =  channleList.size();
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
            columnTextView.setText(channleList.get(i).getName());
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
//                    Toast.makeText(getApplicationContext(), newsClassify.get(v.getId()).getTitle(), Toast.LENGTH_SHORT).show();
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

        int count =  channleList.size();
        for(int i = 0; i< count;i++){
            HotTopicFragment newfragment = new HotTopicFragment();
            newfragment.setHotText(channleList.get(i).getName());
            fragments.add(newfragment);
        }

        //初始化Fragment
//        reFragment = new RecommendFragment();
//        foFragment = new FollowFragment();
//        hotFragment = new HotTopicFragment();
//        spFragment = new SportFragment();
//        flFragment = new FilmFragment();
        // 添加到集合中
//        fragments.add(reFragment);
//        fragments.add(foFragment);
//        fragments.add(hotFragment);
//        fragments.add(spFragment);
//        fragments.add(flFragment);

        mAdapetr = new NewsFragmentPagerAdapter(getApplicationContext(), getSupportFragmentManager(), fragments);
//		mViewPager.setOffscreenPageLimit(0);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.setOnPageChangeListener(pageListener);
    }

    private void initBroadCast() {
        // 创建一个广播接收者
        channelBroadcastReciever = new ChannelBroadcastReciever();
        // 构建一个IntentFilter意图过滤器
        IntentFilter filter = new IntentFilter();
        // 添加Action表明该广播接收器能收什么类型的广播
        filter.addAction("com.topnews.adapter.channel");
        // 注册广播接收器
        registerReceiver(channelBroadcastReciever,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 不再使用广播接收器时，需要解除绑定。
        unregisterReceiver(channelBroadcastReciever);
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
            HotTopicFragment hotTopicFragment = (HotTopicFragment) fragments.get(position);
            hotTopicFragment.setHotText(channleList.get(position).getName());
            if (mAdapetr != null) {
                mAdapetr.setNotifyFragments();
            }
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

    /**
     * @Description:更新tabcolumn
     *
     * @param
     *
     * @return
     *
     */
    private void reTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count =  channleList.size();
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
            columnTextView.setText(channleList.get(i).getName());
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
//                    Toast.makeText(getApplicationContext(), newsClassify.get(v.getId()).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(columnTextView, i ,params);
        }
    }

    /**
     * @Description:更新fragment
     *
     * @param
     *
     * @return
     *
     */
    private void reFragment() {

        if (fragments != null && mAdapetr != null) {
            mAdapetr.setFragments(fragments);
        }
        fragments.clear();
        mViewPager.removeAllViews();
        int count = channleList.size();
        for(int i = 0; i< count;i++){
            HotTopicFragment newfragment = new HotTopicFragment();
            newfragment.setHotText(channleList.get(i).getName());
//            mViewPager.findViewWithTag()
//            String key = "tvRecord" + i;
//            TextView fragment_name = (TextView) mViewPager.findViewWithTag(channleList.get(i).getName());
//            if (fragment_name != null){
//                fragment_name.setText(channleList.get(i).getName());
//            }
            fragments.add(newfragment);
        }
//        mAdapetr.destroyItem();
        if (fragments != null && mAdapetr != null) {
            mAdapetr.setFragments(fragments);
        }

//        mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
//        mViewPager.setAdapter(mAdapetr);
    }
}
