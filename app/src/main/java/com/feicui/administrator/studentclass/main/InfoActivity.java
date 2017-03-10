package com.feicui.administrator.studentclass.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.base.BaseActivity;
import com.feicui.administrator.studentclass.entity.LoginStudent;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/12/27.
 */

public class InfoActivity extends BaseActivity {
    LoginStudent mLoginStudent;
    //姓名
    private TextView tv_name;
    //学号
    private TextView tv_num;
    //性别
    private TextView tv_sex;
    //宿舍
    private TextView tv_room;
    //民族
    private TextView tv_nation;
    //生日
    private TextView tv_born;
    //电话
    private TextView tv_phone;
    //批次
    private TextView tv_pici;
    //qq
    private TextView tv_qq;
    //高中
    private TextView tv_hignschool;

    private ImageView im;
    @Override
    protected boolean fullscreen() {
        return false;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_info;
    }

    @Override
    protected void initView() {
        tv_name = (TextView) findViewById(R.id.info_user_name);
        tv_num = (TextView) findViewById(R.id.info_user_num);
        tv_sex = (TextView) findViewById(R.id.info_user_sex);
        tv_room = (TextView) findViewById(R.id.info_user_room);
        tv_nation = (TextView) findViewById(R.id.info_user_nation);
        tv_born = (TextView) findViewById(R.id.info_user_born);
        tv_phone = (TextView) findViewById(R.id.info_user_connection);
        tv_pici = (TextView) findViewById(R.id.info_user_pici);
        tv_qq = (TextView) findViewById(R.id.info_user_qq);
        tv_hignschool = (TextView) findViewById(R.id.info_user_highschool);
        im= (ImageView) findViewById(R.id.info_user_back_im);
        initinfo();
    }

    @Override
    protected void setListener() {
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initinfo() {
        Intent intent = getIntent();
        mLoginStudent = (LoginStudent) intent.getSerializableExtra("info");
        tv_name.setText(mLoginStudent.getName());
        tv_num.setText(mLoginStudent.getNumber());
        tv_sex.setText(mLoginStudent.getSex());
        tv_room.setText(mLoginStudent.getRoom());
        tv_nation.setText(mLoginStudent.getNation());
        tv_born.setText(mLoginStudent.getBorn());
        tv_phone.setText(mLoginStudent.getPhone());
        tv_pici.setText(mLoginStudent.getPici());
        tv_qq.setText(mLoginStudent.getQq());
        tv_hignschool.setText(mLoginStudent.getHignschool());
    }
}
