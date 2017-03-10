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
 * Created by Administrator on 2016/11/2.
 */
public class ChooseAdapter extends BaseAdapter {

    Context context;

    ArrayList<MyClass> list;

    XListView mXListView;
    int b=1;

    public int getI(){
        return b;
    }

    public ArrayList<MyClass> getList() {
        return list;
    }

    public void setList(ArrayList<MyClass> list) {
        this.list = list;
    }

    public void addSingle(MyClass css){
        list.add(css);
    }

    public void add(ArrayList<MyClass> classes){
        list.addAll(classes);
    }

    public ChooseAdapter(Context context, ArrayList<MyClass> list, XListView XListView) {
        this.context = context;
        this.list = list;
        mXListView = XListView;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup group) {
        ViewHolder holder=null;
        if(view==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            view=inflater.inflate(R.layout.item_chooser,null);
            holder=new ViewHolder();
            holder.mTextView= (TextView) view.findViewById(R.id.choose_item_tv_test);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.mTextView.setText(list.get(i).getName());
        return view;
    }

    public class ViewHolder{
        TextView mTextView;
    }

}
