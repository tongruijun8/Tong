package com.trj.tlib.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.tencent.bugly.Bugly;
import com.trj.tlib.R;
import com.trj.tlib.app.InitApplication;
import com.trj.tlib.manage.ActivityManager;
import com.trj.tlib.manage.NetWorkManage;
import com.trj.tlib.tdialog.TLoadingDialog;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.ToastUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;


/**
 * @author tong
 * @date 2018/3/16 15:52
 */

public class InitActivity extends AppCompatActivity {

    public Context context;
    public View rootView;
    public View rootMask;
    public InitApplication application;
    public ActivityManager activityManager;
    public NetWorkManage netWorkManage;
    public Gson gson;

    protected ViewStub rootTitleViewStub;
    private ViewStub rootContentViewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.layout_root);
        rootTitleViewStub = findViewById(R.id.root_title_viewstub);
//        rootTitle = findViewById(R.id.root_title);
        rootContentViewStub = findViewById(R.id.root_content_viewstub);
        rootMask = findViewById(R.id.root_mask);



//        初始化工作
        initWork();
//        添加内容View
        initContentView(layoutResID);
        //初始化View
        initView();

    }

    /**
     * 注册EventBus
     */
    public void registerEventBus(){
        EventBus.getDefault().register(this);
    }


    /**
     * 设置Buggly 的 appid
     * @param bugglyAppid
     */
    public void setBugglyAppid(String bugglyAppid){

        Bugly.init(context,bugglyAppid,false);
    }

    /**
     * 初始化标题布局
     *
     * @param defaultLayout 是否是默认的布局
     */
    protected void initTitleView(boolean defaultLayout) {
        if (defaultLayout) {
            rootTitleViewStub.setLayoutResource(R.layout.ttitle);
            rootTitleViewStub.inflate();
        }
    }

    /**
     * 初始化内容布局
     *
     * @param layoutResID
     */
    private void initContentView(int layoutResID) {
        rootContentViewStub.setLayoutResource(layoutResID);
        rootContentViewStub.inflate();
    }

    /**
     * Activity的初始化方法
     */
    private void initWork() {
        this.context = this;
        application = (InitApplication) getApplication();
        netWorkManage = NetWorkManage.getInstance();
        activityManager = ActivityManager.getInstance();
        activityManager.addActivity(this);
        gson = new Gson();
        rootView = findViewById(android.R.id.content);

    }

    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        setStateBarColor(R.color.colorwhite);
    }

    public void setStateBarColor(@ColorRes int colorRes) {
        String brand = Build.BRAND;
        Logger.t("--####--" + brand);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (brand.contains("Xiaomi")) {
                if (colorRes == R.color.colorwhite) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    Window window = getWindow();
                    int flag = window.getDecorView().getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    window.getDecorView().setSystemUiVisibility(flag);
                }
            } else if(Build.MANUFACTURER.equalsIgnoreCase("OPPO")){
                //OPPO
                if (colorRes == R.color.colorwhite) {
                    setOPPOStatusTextColor(true, this);
                }else {
                    setOPPOStatusTextColor(false, this);
                }
            }  else {
                Window window = getWindow();
                window.setStatusBarColor(context.getResources().getColor(colorRes));
            }
        }
    }

//    下面是调用状态栏 是否为darkmode。
    //miui7.7.13 版本后放弃
//    public void setStatusBarDarkMode(boolean darkmode, Activity activity) {
//        Class<? extends Window> clazz = activity.getWindow().getClass();
//        try {
//            int darkModeFlag = 0;
//            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
//            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
//            darkModeFlag = field.getInt(layoutParams);
//            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
//            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 设置OPPO手机状态栏字体为黑色(colorOS3.0,6.0以下部分手机)
     *
     * @param lightStatusBar
     * @param activity
     */
    private final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010;

    private void setOPPOStatusTextColor(boolean lightStatusBar, Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int vis = window.getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lightStatusBar) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (lightStatusBar) {
                vis |= SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            } else {
                vis &= ~SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            }
        }
        window.getDecorView().setSystemUiVisibility(vis);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backBefore()) {
                finish();
            }
        }
        return true;
    }

    /**
     * 返回之前(默认返回上一层)
     */
    protected boolean backBefore() {
        return true;
    }

    @Override
    protected void onStop() {
        hideDialog();
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    //    private List<String> selectedPhotos = new ArrayList<>();
    protected void selectImage(int count) {
        if (count > 0) {
//            PhotoPicker.builder()
//                    //设置图片选择数量
//                    .setPhotoCount(count)
//                    //选择界面第一个显示拍照按钮
//                    .setShowCamera(true)
//                    //取消选择时点击图片浏览
//                    .setPreviewEnabled(false)
//                    //开启裁剪
////                .setCrop(true)
//                    //设置裁剪比例(X,Y)
////                .setCropXY(1, 1)
//                    //设置裁剪界面标题栏颜色，设置裁剪界面状态栏颜色
////                .setCropColors(R.color.colorPrimary, R.color.colorPrimaryDark)
//                    .start(this);

            Set<MimeType> mimeTypes = new HashSet<>();
            mimeTypes.add(MimeType.JPEG);
            mimeTypes.add(MimeType.PNG);
            Matisse.from(this)
                    .choose(mimeTypes)
                    .theme(R.style.AppTheme_NoActionBar_welcome)
                    .countable(false)
                    .maxSelectable(count)
                    .originalEnable(true)
                    .maxOriginalSize(10)
//                    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f)
                    .imageEngine(new GlideEngine())
                    .forResult(10001);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //选择返回
        if (resultCode == RESULT_OK && requestCode == 10001) {
//            Matisse.obtainResult(data),
//                    Matisse.obtainOriginalState(data)
            List<String> photos = Matisse.obtainPathResult(data);
            if (photos != null && photos.size() > 0) {
                backImage(photos);
            }

//            if (data != null) {
//                ArrayList<String> photos =
//                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
//                if (photos != null) {
////                selectedPhotos.addAll(photos);
//                    backImage(photos);
//                }
//            }
        }


//        if (resultCode == RESULT_OK &&
//                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
//            List<String> photos = null;
//            if (data != null) {
//                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
//            }
////            selectedPhotos.clear();
//            if (photos != null) {
////                selectedPhotos.addAll(photos);
//                backImage(photos);
//            }
//        }
//        //拍照功能或者裁剪后返回
//        if (resultCode == RESULT_OK && requestCode == PhotoPicker.CROP_CODE) {
//            backImagePhotoOrCrop(data.getStringExtra(PhotoPicker.KEY_CAMEAR_PATH));
//        }

    }

    /* 选择照片返回 */
    protected void backImage(List<String> photos) {

    }

//    /* 拍照功能或者裁剪后返回 */
//    protected void backImagePhotoOrCrop(String path){
//
//    }


    /**
     * 绑定列表数据，资格证，身份证，社区，课程，趣生活等图片
     * (不需要频繁变更的,默认图片为圆形图片)
     *
     * @param path
     * @param imageView
     */
    public void bindImageView(Object path, ImageView imageView) {
        this.bindImageView(path, imageView, true);
    }

    /**
     * 绑定列表数据，资格证，身份证，社区，课程，趣生活等图片
     * (不需要频繁变更的)
     *
     * @param path
     * @param imageView
     * @param isCircle  是否为圆形图片
     */
    public void bindImageView(Object path, ImageView imageView, boolean isCircle) {
        if (isCircle) {
            Glide.with(application.getApplicationContext()).load(path).apply(RequestOptions.bitmapTransform(new CircleCrop()).error(R.mipmap.default_img)).into(imageView);
        } else {
            Glide.with(application.getApplicationContext()).load(path).into(imageView);
        }
    }

    //带圆角
    public void bindImageView(Object path, ImageView imageView, int radius) {
        Glide.with(application.getApplicationContext()).load(path).apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)).error(R.mipmap.default_img)).into(imageView);
    }


    protected TLoadingDialog dialog = null;

    public void showDialog() {
        showDialog("加载中...");
    }

    public void showDialog(String msg) {
        if (dialog == null) {
            dialog = new TLoadingDialog.Builder(context)
                    .setCancelable(true)
                    .setGravity(Gravity.CENTER)
                    .setMessage(msg)
                    .create();
            dialog.show(getSupportFragmentManager(), "tishi_post");
        } else {
            dialog.setMessage(msg);
        }

    }

    public void hideDialog() {
        if (null != dialog) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    //退出系统
    public boolean exitApp() {
        if (exit) {
            activityManager.exit();
        } else {
            ToastUtil.showToast(context, "再次点击退出");
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    exit = true;
                    handler.sendEmptyMessageDelayed(1, 2000);
                }
            }.start();
        }
        return true;
    }

    private boolean exit = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int whats = msg.what;
            if (whats == 1) {
                exit = false;
            }
        }
    };

    protected String phoneNum = "";

    public void callPhoneNumber() {
        //我们需要告诉系统，我们的动作：我要打电话
        //创建意图对象
        Intent intent = new Intent();
        //把打电话的动作ACTION_CALL封装至意图对象当中
        intent.setAction(Intent.ACTION_CALL);
        //设置打给谁
        intent.setData(Uri.parse("tel:" + phoneNum));//这个tel：必须要加上，表示我要打电话。否则不会有打电话功能，由于在打电话清单文件里设置了这个“协议”

        //把动作告诉系统,启动系统打电话功能。
        startActivity(intent);
    }

    protected String getVersion() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
//        setActivityAnim(android.R.anim.fade_in, android.R.anim.fade_out);
//        setActivityAnim(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        setStartActivityAnim(R.anim.activity_in, R.anim.activity_out);
    }

    @Override
    public void finish() {
        super.finish();
//        setActivityAnim(android.R.anim.fade_in, android.R.anim.fade_out);
//        setActivityAnim(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        Logger.t("activityName = "+getClass().getName());
        setEndActivityAnim(R.anim.activity_finish_in, R.anim.activity_finish_out);
    }

    /**
     * 开始activity的动画
     * 更改动画，可以重写此方法
     * @param enterAnim
     * @param exitAnim
     */
    public void setStartActivityAnim(int enterAnim, int exitAnim){
        overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * 结束activity的动画
     * 更改动画，可以重写此方法
     * @param enterAnim
     * @param exitAnim
     */
    public void setEndActivityAnim(int enterAnim, int exitAnim){
        overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * 跳转activity
     * @param cls
     */
    public void skipActivity(Class<?> cls){
        startActivity(new Intent(context,cls));
    }

}
