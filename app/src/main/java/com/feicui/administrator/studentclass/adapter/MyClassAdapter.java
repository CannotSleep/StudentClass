package com.feicui.administrator.studentclass.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.entity.MyClass;
import com.feicui.administrator.studentclass.view.Xlistview.XListView;

import java.util.ArrayList;

/**
 * Created by Z on 2017/3/8.
 */

public class MyClassAdapter extends BaseAdapter {
    Context mContext;

    ArrayList<MyClass> mList;

    XListView mXListView;

    int b = 1;

    public MyClassAdapter(Context context, ArrayList<MyClass> list, XListView XListView) {
        mContext = context;
        mList = list;
        mXListView = XListView;
    }

    public int getI() {
        return b;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void update(){
        notifyDataSetChanged();
    }

    public ArrayList<MyClass> getList() {
        return mList;
    }

    public void setList(ArrayList<MyClass> list) {
        this.mList = list;
    }

    public void addSingle(MyClass css) {
        mList.add(css);
    }

    public void add(ArrayList<MyClass> classes) {
        mList.addAll(classes);
    }

    @Override
    public View getView(int i, View view, ViewGroup group) {
        MyClassAdapter.ViewHolderMyClass viewholder=null;
        if(view==null){
            LayoutInflater layoutInflater=LayoutInflater.from(mContext);
            view=layoutInflater.inflate(R.layout.item_search,null);
            viewholder=new MyClassAdapter.ViewHolderMyClass();
            viewholder.tv_name= (TextView) view.findViewById(R.id.search_item_name);
            viewholder.tv_number= (TextView) view.findViewById(R.id.search_item_number);
            viewholder.tv_order= (TextView) view.findViewById(R.id.search_item_order);
            viewholder.tv_point= (TextView) view.findViewById(R.id.search_item_point);
            viewholder.tv_property= (TextView) view.findViewById(R.id.search_item_property);
            view.setTag(viewholder);
        }else{
            viewholder= (MyClassAdapter.ViewHolderMyClass) view.getTag();
        }
        viewholder.tv_name.setText(mList.get(i).getName());
        viewholder.tv_property.setText(mList.get(i).getProperty());
        viewholder.tv_number.setText("课程号:"+mList.get(i).getNumber());
        viewholder.tv_point.setText("学分:"+mList.get(i).getPoint());
        viewholder.tv_order.setText("课程序号:"+mList.get(i).getOrder());
        return view;
    }

    public class ViewHolderMyClass {
        TextView tv_name;
        TextView tv_number;
        TextView tv_property;
        TextView tv_point;
        TextView tv_order;
    }
}
