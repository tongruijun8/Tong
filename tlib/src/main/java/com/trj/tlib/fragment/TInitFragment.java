package com.trj.tlib.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trj.tlib.activity.InitActivity;
import com.trj.tlib.module.xlistviewmodule.TXListViewListenter;


/**
 * Created by Administrator on 2017/10/20.
 */

public abstract class TInitFragment extends Fragment implements TXListViewListenter {

    public InitActivity activity;

    public TInitFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (InitActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(layoutRes, container, false);
        initFragmentView(rootView);
        return rootView;
    }

    private int layoutRes = -1;

    protected void initlayout(@LayoutRes int resource){
        layoutRes = resource;
    }

    protected abstract void initFragmentView(View view);

    /**
     * 初始化数据
     */
    @Deprecated
    public abstract void initData();


    @Override
    public void exceptionPageClickEvent() {

    }

    /**
     * 获取XListView列表数据
     * @param page 页码
     */
    @Override
    public void getData(int page) {

    }

}
