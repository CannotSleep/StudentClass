package com.feicui.administrator.studentclass.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.feicui.administrator.studentclass.R;


/**
 * Created by Administrator on 2017/1/5.
 */

public class MyToast {
    private static TextView mTextView;
    private static ImageView mImageView;

    public static void showToast(Context context, String message) {
        //加载Toast布局
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.my_toast, null);
        //初始化布局控件
        mTextView = (TextView) toastRoot.findViewById(R.id.mytoast_message);
        mImageView = (ImageView) toastRoot.findViewById(R.id.mytoast_imageView);
        //为控件设置属性
        mTextView.setText(message);
        mImageView.setImageResource(R.drawable.mytoastim);
        //Toast的初始化
        Toast toastStart = new Toast(context);
        //获取屏幕高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.TOP, 0, height / 2);
        toastStart.setDuration(Toast.LENGTH_SHORT);
        toastStart.setView(toastRoot);
        toastStart.show();
    }
}