package com.trj.tlib.fragment;

import android.view.View;

import com.trj.tlib.module.titlemodule.TitleListenter;
import com.trj.tlib.module.titlemodule.TitleModule;


/**
 * Created by Administrator on 2017/10/20.
 */

public abstract class TInitTitleFragment extends TInitFragment implements TitleListenter {

    protected TitleModule titleModule;

    @Override
    protected void initFragmentView(View view) {
        titleModule = new TitleModule(activity.context,view);
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
}
