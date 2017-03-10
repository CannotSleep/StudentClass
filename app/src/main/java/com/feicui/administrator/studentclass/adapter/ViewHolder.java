package com.feicui.administrator.studentclass.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/18.
 */
public class ViewHolder {
    private View mContentView;
    private SparseArray<View> mViews;// 装控件
    private int mPosition;
    private Context context;

    public static ViewHolder newInstance(Context context, View convertView,
                                         int layoutId, ViewGroup group, int position) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder(context, layoutId, group, position);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
        }
        return holder;
    }

    private ViewHolder(Context context, int layoutId, ViewGroup group,
                       int position) {
        this.context = context;
        mViews = new SparseArray<View>();
        this.mPosition = position;

        mContentView = LayoutInflater.from(context).inflate(layoutId, group,
                false);
        mContentView.setTag(this);
    }

    public <T extends View> T getViewById(int resId) {
        View view = mViews.get(resId);
        if (null == view) {
            view = mContentView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    /**
     * 为textview设置内容
     *
     * @param resId
     * @param text
     */
    public void setTextForTv(int resId, String text) {
        TextView tv = getViewById(resId);
        tv.setText(text);
    }

    /**
     * 设置文字颜色
     *
     * @param resId
     * @param colorId
     */
    public void setTextColor(int resId, int colorId) {
        TextView tv = getViewById(resId);
        tv.setTextColor(colorId);
    }

    public void setImageResource(int resId, int imgId) {
        ImageView img = getViewById(resId);
        img.setImageResource(imgId);
    }

    public void setImageBitmap(int resId, Bitmap bitmap) {
        ImageView img = getViewById(resId);
        img.setImageBitmap(bitmap);
        if (null != bitmap) {
            bitmap.recycle();
        }
    }

    public View getConvertView() {
        return mContentView;
    }

    public int getPosition() {
        return mPosition;
    }
}
