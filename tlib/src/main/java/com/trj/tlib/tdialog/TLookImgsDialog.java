package com.trj.tlib.tdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trj.tlib.R;
import com.trj.tlib.adapter.Text1Adapter;
import com.trj.tlib.views.TListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的 Dialog 布局
 */
public class TLookImgsDialog extends BaseDialog implements View.OnClickListener {

    private ViewHolder viewHolder;
    private View contentView;
    private Builder builder;


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.td_look_imageview) {
            if (builder.listener != null) {
                builder.listener.onTdImgClick();
            }
            dismiss();
        }
    }

    public static class Builder {
        private Context context;

//        protected List<String> stringList;

        protected Object pathImg;
        protected OnTdLookImgClickListener listener;

        private LayoutInflater inflater;

        public Builder(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        public Builder setImgPath(Object imgPath) {
            this.pathImg = imgPath;
            return this;
        }

//        public Builder setList(List<String> stringList) {
//            if (stringList != null && stringList.size() > 0) {
//                this.stringList.addAll(stringList);
//            }
//            return this;
//        }

        public Builder setOnTdLookImgClick(OnTdLookImgClickListener onTdLookImgClickListener) {
            this.listener = onTdLookImgClickListener;
            return this;
        }

        public TLookImgsDialog create() {
            TLookImgsDialog dialog = new TLookImgsDialog();
            dialog.builder = this;
            dialog.contentView = inflater.inflate(R.layout.td_look_img, null);
            dialog.viewHolder = new ViewHolder(dialog.contentView);
            Glide.with(context).load(pathImg).into(dialog.viewHolder.tdLookImageView);
            return dialog;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewHolder.tdLookImageView.setOnClickListener(this);
        return contentView;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void onStart() {
        super.onStart();
        //经测试，在这里设置背景色才起作用
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        //让宽度充满屏幕
        lp.width = getResources().getDisplayMetrics().widthPixels;
        lp.height = getResources().getDisplayMetrics().heightPixels;
        window.setAttributes(lp);
    }

    static class ViewHolder {
        View view;
        ImageView tdLookImageView;

        ViewHolder(View view) {
            this.view = view;
            tdLookImageView = view.findViewById(R.id.td_look_imageview);

        }
    }

}
