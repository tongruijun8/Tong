package com.trj.tlib.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.trj.tlib.R;
import com.trj.tlib.adapter.TViewPagerAdapter;
import com.trj.tlib.fragment.TInitFragment;
import com.trj.tlib.views.TViewPager;

import java.util.ArrayList;
import java.util.List;

public class NavBottomActivity extends InitActivity implements BottomNavigationView.OnNavigationItemReselectedListener, BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    protected BottomNavigationView mBottomNavView;
    protected TViewPager mViewpager;

    //fragment数据集合
    protected List<TInitFragment> fragmentList = new ArrayList<>();

    private TViewPagerAdapter tViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bottom);
    }

    @Override
    protected void initView() {
        mBottomNavView = findViewById(R.id.bottom_nav_view);
        mViewpager = findViewById(R.id.viewpager);
        mViewpager.setScanScroll(false);

        mBottomNavView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

//        1.取消位移动画的效果
//        activityAssist.disableShiftMode(mBottomNavView);
//        2.取消导航栏的点击效果（类似水波纹的效果）：app:itemBackground="@null"
//        3.取消导航栏的每项点击文字和图片放大的效果：我们需要在values中的demens.xml中设置相应的值

        mBottomNavView.setOnNavigationItemSelectedListener(this);
        mBottomNavView.setOnNavigationItemReselectedListener(this);
        initFragmentData();
        initTabData();
        tViewPagerAdapter = new TViewPagerAdapter(getSupportFragmentManager());
        tViewPagerAdapter.setmFragments(fragmentList);
        mViewpager.setAdapter(tViewPagerAdapter);
        mViewpager.setOffscreenPageLimit(fragmentList.size());
        mViewpager.setScanScroll(false);
        mViewpager.addOnPageChangeListener(this);
    }


    protected void initFragmentData() {
        //默认
//        if (fragmentList == null || fragmentList.size() == 0) {
//            fragmentList.add(BlankFragment.newInstance("首页", ""));
//            fragmentList.add(BlankFragment.newInstance("订单", ""));
//            fragmentList.add(BlankFragment.newInstance("消息", ""));
//            fragmentList.add(BlankFragment.newInstance("我的", ""));
//        }
    }

    protected void initTabData() {
        //默认
//        mBottomNavView.inflateMenu(R.menu.navigation_bottom);
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }

    protected MenuItem menuItem;
    protected int menuItemPosition = 0;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        menuItem = item;
        int itemId = item.getItemId();
        if (itemId == R.id.nav_bottom_item1) {
            fragmentList.get(0).initData();
            mViewpager.setCurrentItem(0, false);
            menuItemPosition = 0;
            return true;
        } else if (itemId == R.id.nav_bottom_item2) {
            fragmentList.get(1).initData();
            mViewpager.setCurrentItem(1, false);
            menuItemPosition = 1;
            return true;
        } else if (itemId == R.id.nav_bottom_item3) {
            fragmentList.get(2).initData();
            mViewpager.setCurrentItem(2, false);
            menuItemPosition = 2;
            return true;
        } else if (itemId == R.id.nav_bottom_item4) {
            fragmentList.get(3).initData();
            mViewpager.setCurrentItem(3, false);
            menuItemPosition = 3;
            return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            mBottomNavView.getMenu().getItem(0).setChecked(false);
        }
        menuItem = mBottomNavView.getMenu().getItem(position);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
