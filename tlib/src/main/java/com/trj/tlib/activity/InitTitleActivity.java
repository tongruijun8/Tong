package com.trj.tlib.activity;

import android.os.Bundle;
import android.view.View;

import com.trj.tlib.module.titlemodule.TitleListenter;
import com.trj.tlib.module.titlemodule.TitleModule;
import com.trj.tlib.module.xlistviewmodule.TXListViewListenter;

/**
 * 加载自定义标题栏的activity
 *
 * 注：①实现XListView的接口，用于XListView加载数据；
 *      （TXListViewListenter的使用需要配合TXListViewModule来使用，且布局中需要包含layout_xlistview.xml布局）
 */
public class InitTitleActivity extends InitActivity  implements TitleListenter, TXListViewListenter {

    public TitleModule titleModule;

    /* 默认的标题布局 */
    private boolean isDefaultTitleLayout = false;

    public void setDefaultTitleLayout(boolean defaultTitleLayout) {
        isDefaultTitleLayout = defaultTitleLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        initTitleView(isDefaultTitleLayout);
        titleModule = new TitleModule(context, rootView);
        titleModule.setListenter(this);
    }

    @Override
    public void onClickBack(View view) {

    }

    @Override
    public void onClickLeftText(View view) {

    }

    @Override
    public void onClickRightText(View view) {

    }

    @Override
    public void onClickMenu(View view) {

    }

    @Override
    public void onMenuItemClick(int position) {

    }

    @Override
    public void exceptionPageClickEvent() {

    }

    @Override
    public void getData(int page) {

    }
}
