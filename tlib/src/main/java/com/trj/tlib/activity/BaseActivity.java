package com.trj.tlib.activity;

import android.os.Bundle;

import com.trj.tlib.module.xlistviewmodule.TXListViewListenter;

/**
 * @author tong
 * @date 2018/3/18 10:46
 */

public class BaseActivity extends InitActivity implements TXListViewListenter{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void exceptionPageClickEvent() {
        getData(1);
    }

    @Override
    public void getData(int page) {

    }


}
