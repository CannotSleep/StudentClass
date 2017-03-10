package com.feicui.administrator.studentclass.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.base.BaseActivity;
import com.feicui.administrator.studentclass.db.DbManager;
import com.feicui.administrator.studentclass.util.MyToast;

/**
 * Created by Z on 2017/3/8.
 */

public class ChangePassWordActivity extends BaseActivity implements View.OnClickListener{
    EditText edt_one;
    EditText edt_two;
    Button change_btn;
    ImageView imageView_back;
    @Override
    protected boolean fullscreen() {
        return false;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_changepassword;
    }

    @Override
    protected void initView() {
        edt_one= (EditText) findViewById(R.id.change_one_edt);
        edt_two= (EditText) findViewById(R.id.change_two_edt);
        change_btn= (Button) findViewById(R.id.change_btn);
        imageView_back= (ImageView) findViewById(R.id.change_ps_back_im);
    }

    @Override
    protected void setListener() {
        change_btn.setOnClickListener(this);
        imageView_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change_btn:
                String a=edt_one.getText().toString();
                String b=edt_two.getText().toString();
                if(judge(a,b)){
                    change(a);
                }
            break;
            case R.id.change_ps_back_im:
                finish();
            break;
        }
    }

    public boolean judge(String stra,String strb){
        if(stra!=null){
            if(strb!=null){
                    if(stra.matches("^[0-9a-zA-Z]{6,11}$")){
                        if(stra.equals(strb)){
                            return true;
                        }
                    }else{
                        MyToast.showToast(this,"密码必须为0-9和A到Z的大小写字母");
                    }
            }else{
                MyToast.showToast(this,"第二次输入密码不能为空");
            }
        }else{
            MyToast.showToast(this,"密码不能为空");
        }
        return false ;
    }

    public void change(String ps){
        Intent intent =getIntent();
        String num=intent.getStringExtra("changenumber");
        DbManager dbManager = new DbManager(this);
        if(dbManager.changePass(num,ps)){
           MyToast.showToast(this,"修改成功");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
