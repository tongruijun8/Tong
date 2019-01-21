package com.trj.tlib.tdialog;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.view.LayoutInflater;

import com.trj.tlib.R;
import com.trj.tlib.adapter.Text1Adapter;

import java.util.ArrayList;
import java.util.List;

public class TBuilder {

    private Context context;
    @FloatRange(from = 0.0, to = 1.0)
    protected float alpha;
    protected String cancleText;

    protected boolean cancelable;// 默认不可以取消

    public LayoutInflater inflater;


    public TBuilder(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        alpha = 0.4f;
        cancelable = false;
    }

    public TBuilder setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public TBuilder setCancleText(String cancleText) {
        this.cancleText = cancleText;
        return this;
    }

    public TBuilder setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    
}
