package com.feicui.administrator.studentclass.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.db.DbManager;
import com.feicui.administrator.studentclass.entity.LoginStudent;
import com.feicui.administrator.studentclass.main.ChangePassWordActivity;
import com.feicui.administrator.studentclass.main.InfoActivity;
import com.feicui.administrator.studentclass.main.LoginActivity;
import com.feicui.administrator.studentclass.main.MainActivity;
import com.feicui.administrator.studentclass.main.MyClassActivity;

/**
 * Created by Administrator on 2016/10/18.
 */
public class Fragmentuser extends Fragment implements View.OnClickListener{
    //姓名
    TextView tv_name;
    //学号
    TextView tv_number;

    MainActivity main;
    //数据库管理类
    DbManager mDbManager;
    //退出登陆
    TextView tv_exit;
    //我的信息
    TextView tv_info;
    LinearLayout mLayout;
    LinearLayout change_layout;
    LoginStudent student;
    LinearLayout myclass_layout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        tv_name= (TextView) view.findViewById(R.id.user_name);
        tv_number= (TextView) view.findViewById(R.id.user_number);
        tv_exit= (TextView) view.findViewById(R.id.user_exit_tv);
        tv_info= (TextView) view.findViewById(R.id.user_my_info);
        mLayout= (LinearLayout) view.findViewById(R.id.user_my_info_linea);
        change_layout= (LinearLayout) view.findViewById(R.id.user_linea_changeps);
        myclass_layout= (LinearLayout) view.findViewById(R.id.user_my_class_linea);
        mLayout.setOnClickListener(this);
        change_layout.setOnClickListener(this);
        myclass_layout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        main = (MainActivity) getActivity();
        mDbManager=new DbManager(getContext());
        tv_exit.setOnClickListener(this);

        initUI();
    }

    public void initUI(){
        String number=main.number;
        tv_number.setText(number);
        student=mDbManager.searchLogin(number);
        tv_name.setText(student.getName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_exit_tv:
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
               getActivity().finish();
            break;
            case R.id.user_my_info_linea:
                Intent intent_a = new Intent(getActivity(), InfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info",student);
                intent_a.putExtras(bundle);
                startActivity(intent_a);
                break;
            case R.id.user_linea_changeps:
                Intent intent_b=new Intent(getActivity(), ChangePassWordActivity.class);
                intent_b.putExtra("changenumber",student.getNumber());
                startActivity(intent_b);
            break;
            case R.id.user_my_class_linea:
                Intent intent_c=new Intent(getActivity(), MyClassActivity.class);
                intent_c.putExtra("myclass",student.getNumber());
                startActivity(intent_c);
            break;
        }
    }
}
