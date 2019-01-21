package com.trj.tlib.activity.presenters;

import android.content.Context;
import android.view.View;

public abstract class PInitPresenter {

    protected Context context;

    public PInitPresenter(Context context) {
        this.context = context;
    }

    protected abstract void initView(View rootView);

}
