package com.trj.tlib.activity;

import android.os.Bundle;
import android.view.View;

import com.trj.tlib.module.titlemodule.TitleListenter;
import com.trj.tlib.module.titlemodule.TitleModule;

public class BaseTitleActivity extends BaseActivity implements TitleListenter {

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
        finish();
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
}
