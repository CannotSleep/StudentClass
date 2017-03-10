package com.feicui.administrator.studentclass.main;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.adapter.MyClassAdapter;
import com.feicui.administrator.studentclass.base.BaseActivity;
import com.feicui.administrator.studentclass.db.DbManager;
import com.feicui.administrator.studentclass.db.LoginEntry;
import com.feicui.administrator.studentclass.entity.MyClass;
import com.feicui.administrator.studentclass.util.MyToast;
import com.feicui.administrator.studentclass.view.Xlistview.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Z on 2017/3/8.
 */

public class MyClassActivity extends BaseActivity implements View.OnClickListener,PopupWindow.OnDismissListener{
    ImageView myclass_back_im;
    XListView xx_myclass;
    ArrayList<MyClass>  mList;
    String number;
    MyClassAdapter myClassAdapter;
    int navigationHeight;
    private PopupWindow popupWindow;
    int o=0;
    @Override
    protected boolean fullscreen() {
        return false;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_myclass;
    }

    @Override
    protected void initView() {
        myclass_back_im= (ImageView) findViewById(R.id.myclass_back_im);
        xx_myclass= (XListView) findViewById(R.id.myclass_xlistview);
        Intent intent = getIntent();
        number=intent.getStringExtra("myclass");
        initXX();
    }

    @Override
    protected void setListener() {
        myclass_back_im.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.myclass_back_im:
                finish();
            break;
        }
    }



    public void initXX(){
        mList=new ArrayList<MyClass>();
        mList.clear();
        mList=initdata();
        if(mList!=null){
            myClassAdapter=new MyClassAdapter(this,mList,xx_myclass);
            xx_myclass.setAdapter(myClassAdapter);
            xx_myclass.setPullRefreshEnable(true);
            xx_myclass.setPullLoadEnable(true);
            xx_myclass.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
            xx_myclass.setXListViewListener(new XListView.IXListViewListener() {
                @Override
                public void onRefresh() {
                    Log.i("zzz",mList.size()+"");
                    refresh();
                    xx_myclass.stopRefresh();
                }

                @Override
                public void onLoadMore() {
                    loadmore();
                    xx_myclass.stopLoadMore();
                }
            });
            xx_myclass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    o=i;
                    Log.i("bbbbb",i+"");
                    openpopWindow(view);
                }
            });
        }else {
            xx_myclass.setVisibility(View.GONE);
        }

    }

    public void refresh(){

    }

    public void loadmore(){

    }

    public ArrayList<MyClass> initdata(){
        ArrayList<MyClass>  l=new ArrayList<>();
        //通过固定路径获得可读的数据库对象
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
                LoginEntry.DATABASE_PATH + "/student.db", null
        );
        try{
        //查询数据库返回一个游标
        Cursor cursor = database.query(
                //表名
                "A"+number,
                //列名
                new String[]{LoginEntry.COLUMNS_CLASS_NAME,
                        LoginEntry.COLUMNS_CLASS_NUMBER,
                        LoginEntry.COLUMNS_CLASS_ORDER,
                        LoginEntry.COLUMNS_CLASS_POINT,
                        LoginEntry.COLUMNS_CLASS_PROPERTY
                },
                null, //where
                null, //where args
                null, //groups by
                null,//having
                null//order by
        );
        cursor.moveToFirst();
        //装载学生对象的实体数组
        final ArrayList<MyClass> datalist = new ArrayList<MyClass>();
        //读取游标数据
        do {
            //获得课程名字
            String name = cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_CLASS_NAME));
            //获得课程号
            String number = cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_CLASS_NUMBER));
            //获得课程属性
            String property=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_CLASS_PROPERTY));
            //获得课程学分
            String point=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_CLASS_POINT));
            //获得课程序号
            String order=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_CLASS_ORDER));

            MyClass myClass = new MyClass();
            myClass.setName(name);
            myClass.setNumber(number);
            myClass.setProperty(property);
            myClass.setPoint(point);
            myClass.setOrder(order);
            datalist.add(myClass);
        } while (cursor.moveToNext());
            if(datalist!=null){
                l=datalist;
            }
    }catch (Exception e){

        }finally {
            database.close();
        }

      return l;
    }

    public void openpopWindow(View v){
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.myclass_popwindow, null);
        popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置背景,这个没什么效果，不添加会报错
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置动画
        popupWindow.setAnimationStyle(R.style.PopupWindow);
        //设置位置
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, navigationHeight);
        //设置消失监听
        popupWindow.setOnDismissListener(this);
        //设置PopupWindow的View点击事件
        setOnPopupViewClick(view);
        //设置背景色
        setBackgroundAlpha(0.5f);
    }
    //设置背景颜色
    public void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = alpha;
        this.getWindow().setAttributes(lp);
    }
    //设置pop点击事件
    private void setOnPopupViewClick(View view) {
        final DbManager dbManager = new DbManager(this);
        Button cancle_btn,dis;
        cancle_btn= (Button) view.findViewById(R.id.myclass_pop_cancle_btn);
        dis= (Button) view.findViewById(R.id.myclass_cancel);
        cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.cancleclass(number,mList.get(o-1));
                mList.remove(o-1);
                if(mList!=null){
                    MyToast.showToast(MyClassActivity.this ,"退出成功");
                    onDismiss();
                    myClassAdapter.notifyDataSetChanged();
                }else{
                    xx_myclass.setVisibility(View.GONE);
                }

            }
        });
        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDismiss();
            }
        });
    }

    @Override
    public void onDismiss() {
        setBackgroundAlpha(1);
        popupWindow.dismiss();
    }

}
