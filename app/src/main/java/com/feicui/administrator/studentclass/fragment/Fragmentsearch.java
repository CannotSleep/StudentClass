package com.feicui.administrator.studentclass.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.adapter.SearchAdapter;
import com.feicui.administrator.studentclass.db.DbManager;
import com.feicui.administrator.studentclass.db.LoginEntry;
import com.feicui.administrator.studentclass.entity.MyClass;
import com.feicui.administrator.studentclass.main.MainActivity;
import com.feicui.administrator.studentclass.util.MyToast;
import com.feicui.administrator.studentclass.view.Xlistview.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/18.
 */
public class Fragmentsearch extends Fragment implements View.OnClickListener,PopupWindow.OnDismissListener{
    //搜索的适配器
    SearchAdapter mSearchAdapter;
    //查询条件输入框
    EditText edt_one;
    //查询按钮
    ImageView imageView;
    //查询界面中的xlistview
    XListView xx_search;
    //读取出来的课程数组
    ArrayList<MyClass>  mList;
    //搜索条件
    String search;
    String  number;
    MainActivity main;

    private  int o=0;

    private int navigationHeight;

    private PopupWindow popupWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentsearch,container,false);
        edt_one= (EditText) view.findViewById(R.id.fragment_search_edt_one);
        imageView= (ImageView) view.findViewById(R.id.fragment_search_im_one);
        xx_search= (XListView) view.findViewById(R.id.fragment_search_xlistview);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView.setOnClickListener(this);
        main= (MainActivity) getActivity();
        number=main.number;
        intixx();
    }

    public void intixx(){
        mList=new ArrayList<MyClass>();
        mList.clear();
        mList=initdata();
        mSearchAdapter=new SearchAdapter(getContext(),mList,xx_search);
        xx_search.setAdapter(mSearchAdapter);
        xx_search.setPullRefreshEnable(true);
        xx_search.setPullLoadEnable(true);
        xx_search.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
        xx_search.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                Log.i("zzz",mList.size()+"");
                refresh();
                xx_search.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                loadmore();
                xx_search.stopLoadMore();
            }
        });
        xx_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                o=i;
                openpopWindow(view);
                    Log.i("aaaaa",i+"");
            }
        });
    }



    public void refresh(){
        mList.clear();
        mList=initdata();
        mSearchAdapter=new SearchAdapter(getContext(),mList,xx_search);
        xx_search.setAdapter(mSearchAdapter);
    }

    public void loadmore(){
        mList=initdata();
        mSearchAdapter=new SearchAdapter(getContext(),mList,xx_search);
        xx_search.setAdapter(mSearchAdapter);
    }

    public ArrayList<MyClass> initdata(){
        //通过固定路径获得可读的数据库对象
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
                LoginEntry.DATABASE_PATH + "/student.db", null
        );
        //查询数据库返回一个游标
        Cursor cursor = database.query(
                //表名
                LoginEntry.TABLE_CLASS,
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

        return datalist;
    }




    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.fragment_search_im_one:
                Log.i("zzz",mList.size()+"");
                search=edt_one.getText().toString();
                mList.clear();
                mList=searchdata(search);
                mSearchAdapter=new SearchAdapter(getContext(),mList,xx_search);
                xx_search.setAdapter(mSearchAdapter);
            break;

        }
    }

    public ArrayList<MyClass> searchdata(String str){
        //通过固定路径获得可读的数据库对象
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
                LoginEntry.DATABASE_PATH + "/student.db", null
        );
        //查询数据库返回一个游标
        Cursor cursor = database.query(
                //表名
                LoginEntry.TABLE_CLASS,
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
            if(search.equals(point)){
                datalist.add(myClass);
            }
        } while (cursor.moveToNext());

        return datalist;
    }


    public void openpopWindow(View v){
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.design_popwindow, null);
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
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = alpha;
        getActivity().getWindow().setAttributes(lp);
    }
    //设置pop点击事件
    private void setOnPopupViewClick(View view) {
        final DbManager dbManager = new DbManager(getActivity());
        Button choose_btn,cancle_btn,dis;
        choose_btn= (Button) view.findViewById(R.id.choose_btn);
        cancle_btn= (Button) view.findViewById(R.id.cancle_btn);
        dis= (Button) view.findViewById(R.id.cancel);
        int a=view.getId();
        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(dbManager.searchClass(number,mList.get(o).getName())){
                   MyToast.showToast(getActivity(),"您已选择此课程");
               } else{
                   dbManager.chooseclass(number,mList.get(o));
                   MyToast.showToast(getActivity(),"选择成功");
               }
                onDismiss();
            }
        });
        cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbManager.searchClass(number,mList.get(o).getName())){
                    dbManager.cancleclass(number,mList.get(o));
                    MyToast.showToast(getActivity(),"退出成功");
                }else{
                    MyToast.showToast(getActivity(),"您未选择此课程");
                }
                onDismiss();
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