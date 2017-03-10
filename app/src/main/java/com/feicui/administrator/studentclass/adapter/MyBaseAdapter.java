package com.feicui.administrator.studentclass.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> data = new ArrayList<T>();
    private Context context;
    private int layoutId;

    public MyBaseAdapter(Context context, int layoutId, List<T> datas) {
        this(context, layoutId);
        this.data = datas;
    }

    public MyBaseAdapter(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    /**
     * 刷新适配器
     */
    public void update(){
        this.notifyDataSetChanged();
    }


    public int getCount() {
        if(data==null){
            return 0;
        }
        return data.size();
    }


    public T getItem(int position) {
        if(data==null && data.size()==0){
            return null;
        }
        if(position>data.size()){
            return  null;
        }
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.newInstance(context, convertView,
                layoutId, parent, position);
        setViewDetails(holder, getItem(position));

        return holder.getConvertView();
    }
    //要实现的抽象方法，具体为控件赋值
    public abstract void setViewDetails(ViewHolder holder, T data);
}
