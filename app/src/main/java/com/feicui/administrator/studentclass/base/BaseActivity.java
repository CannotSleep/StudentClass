package com.feicui.administrator.studentclass.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/31.
 * Activity 的基类
 */
public abstract class BaseActivity extends FragmentActivity {
    //判断是否为全屏
    Boolean full = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        full = fullscreen();
        if (full) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        //初始化Activity   Initialization Activity

        setContentView(contentView());

        initView();

        setListener();


    }

    protected abstract boolean fullscreen();

    public void log(String some) {
        Log.i("aa", some);
    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }

    /**
     * Loading Layout
     * 加载布局
     *
     * @return 返回布局layout  return Layout resource
     */
    protected abstract int contentView();

    /**
     * Loading Control
     * 加载控件
     */
    protected abstract void initView();

    /**
     * Set Listener
     * 设置监听
     */
    protected abstract void setListener();




}
