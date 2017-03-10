package com.feicui.administrator.studentclass.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.base.BaseActivity;
import com.feicui.administrator.studentclass.db.DbManager;
import com.feicui.administrator.studentclass.db.LoginEntry;
import com.feicui.administrator.studentclass.entity.LoginStudent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/1.
 * 登陆界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    TextView login_tv1;
    TextView login_tv2;
    //密码输入框
    EditText login_num_edt;
    //学号输入框
    EditText login_password_edt;
    String number = "";
    String password = "";
    Button login_btn;
    ArrayList<LoginStudent> datalist;
    DbManager mDbManager;
    LoginStudent mLoginStudent;

    @Override
    protected boolean fullscreen() {
        return false;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mDbManager=new DbManager(this);
        login_tv1 = (TextView) findViewById(R.id.login_tv3);
        login_tv2 = (TextView) findViewById(R.id.login_tv4);
        login_num_edt = (EditText) findViewById(R.id.login_num_edt);
        login_password_edt = (EditText) findViewById(R.id.login_password_edt);
        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setBackgroundResource(R.drawable.shape4);
        login_btn.setEnabled(false);
        importDatabase();
        datalist = initLogin();
        login_password_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str=editable.toString();
                if(!str.equals("")){
                    login_btn.setEnabled(true);
                    login_btn.setBackgroundResource(R.drawable.shape5);
                }
            }
        });
    }

    @Override
    protected void setListener() {
        login_tv1.setOnClickListener(this);
        login_tv2.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

    public void importDatabase() {

        try {
            //创建数据库目录，若数据库目录不存在，创建单层目录
            File dirFile = new File(LoginEntry.DATABASE_PATH);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            //创建将被导入的数据库File对象
            File file = new File(LoginEntry.DATABASE_PATH, "student.db");
            //判断文件是否存在，如不存在则创建该文件，存在就直接返回
            if (!file.exists()) {
                file.createNewFile();
                //      Log.i("aaa","11111");
            } else {
                //      Log.i("aaa","2222");
                return;
            }
            FileOutputStream fop = new FileOutputStream(file);
            //获得自带数据库的输入流
            InputStream ip = getResources().openRawResource(R.raw.student);
            //创建将被导入的数据库输出流

            //创建缓冲区
            //  Log.i("aaa","33333");
            byte[] buffer = new byte[1024];
            //将数据读入缓冲区，并写入输出流
            while (ip.read(buffer) != -1) {
                //将缓冲区中的数据写入输出流
                fop.write(buffer);
                //重置缓冲区
                buffer = new byte[1024];
                //     Log.i("aaa","44444");
            }
            //关闭输入输出流
            fop.flush();
            ip.close();
            fop.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登陆判断
     */
    private boolean studentLogin() {
        //得到edt中用户输入的内容
        number = login_num_edt.getText().toString();
        password = login_password_edt.getText().toString();
        if (!number.equals(null)) {
            if (number.matches("^[0-9]{11}$")) {
                if (!password.equals(null)) {
                    if (password.matches("^[0-9A-Za-z]{0,11}$")) {
                        for (int i = 0; i < datalist.size(); i++) {
                            if (number.equals(datalist.get(i).getNumber()) && password.equals(datalist.get(i).getPassword())) {
                                mLoginStudent=datalist.get(i);
                                return true;
                            }
                        }
                    } else {
                        Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
                        return false;
                    }
                } else {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
                    return false;
                }
            } else {
                Toast.makeText(this, "学号错误", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(this, "学号不能为空", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    public ArrayList<LoginStudent> initLogin() {
        //通过固定路径获得可读的数据库对象
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
                LoginEntry.DATABASE_PATH + "/student.db", null
        );
        //查询数据库返回一个游标
        Cursor cursor = database.query(
                //表名
                LoginEntry.TABLE_NAME,
                //列名
                new String[]{LoginEntry.COLUMNS_NAME_NUMBER,
                        LoginEntry.COLUMNS_NAME_PASSWORD,
                        LoginEntry.COLUMNS_BEGIN_YEAR,
                        LoginEntry.COLUMNS_FAMILY,
                        LoginEntry.COLUMNS_BORN,
                        LoginEntry.COLUMNS_HIGHSCHOOL,
                        LoginEntry.COLUMNS_IDNUM,
                        LoginEntry.COLUMNS_NAME_NAME,
                        LoginEntry.COLUMNS_NATION,
                        LoginEntry.COLUMNS_PARENT,
                        LoginEntry.COLUMNS_PHONE,
                        LoginEntry.COLUMNS_PICI,
                        LoginEntry.COLUMNS_POLITICS,
                        LoginEntry.COLUMNS_QQ,
                        LoginEntry.COLUMNS_ROOM,
                        LoginEntry.COLUMNS_SEX,
                    },
                null, //where
                null, //where args
                null, //groups by
                null,//having
                null//order by
        );
        cursor.moveToFirst();
        //装载学生对象的实体数组
        final ArrayList<LoginStudent> datalist = new ArrayList<LoginStudent>();
        //读取游标数据
        do {
            //获得学号
            String number = cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_NAME_NUMBER));
            //获得密码
            String password = cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_NAME_PASSWORD));
            //获得名字
            String name = cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_NAME_NAME));
            //获得入学年份
            String beginyear=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_BEGIN_YEAR));
            //获得家庭地址
            String family=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_FAMILY));
            //获得出生日期
            String born =cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_BORN));
            //获得毕业学校
            String highschool=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_HIGHSCHOOL));
            //获得身份证号码
            String idnum=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_IDNUM));
            //获得民族
            String nation=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_NATION));
            //获得父母
            String parent=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_PARENT));
            //获得联系方式
            String conection=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_PHONE));
            //获得批次
            String pici=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_PICI));
            //获得政治面貌
            String politic=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_POLITICS));
            //获得qq
            String qq=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_QQ));
            //获得宿舍
            String room=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_ROOM));
            //获得性别
            String sex=cursor.getString(cursor.getColumnIndexOrThrow(LoginEntry.COLUMNS_SEX));

            LoginStudent loginStudent = new LoginStudent();
            loginStudent.setNumber(number);
            loginStudent.setPassword(password);
            loginStudent.setName(name);
            loginStudent.setBeginyear(beginyear);
            loginStudent.setBorn(born);
            loginStudent.setFamily(family);
            loginStudent.setHignschool(highschool);
            loginStudent.setIdnum(idnum);
            loginStudent.setNation(nation);
            loginStudent.setPhone(conection);
            loginStudent.setParent(parent);
            loginStudent.setPici(pici);
            loginStudent.setPolitics(politic);
            loginStudent.setQq(qq);
            loginStudent.setRoom(room);
            loginStudent.setSex(sex);

            datalist.add(loginStudent);
        } while (cursor.moveToNext());

        return datalist;
    }
    /**
     * 按两次退出键退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    //第一次点击的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                //第二次点击的时间
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    firstTime = secondTime;
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
                } else {
                    System.exit(0);
                    return true;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_tv3:
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("提示")
                        .setMessage("请检查网络")
                        .setPositiveButton("确定", null).show();
                break;
            case R.id.login_tv4:
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("提示")
                        .setMessage("请联系管理员")
                        .setPositiveButton("确定", null).show();
                break;
            case R.id.login_btn:
                if (studentLogin()) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("student",mLoginStudent);
                    intent.putExtras(bundle);
                    intent.putExtra("num",number);
                    Log.i("eee",number+"");
                    Log.i("aa",mLoginStudent.getBeginyear()+mLoginStudent.getHignschool());
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    DbManager dbManager = new DbManager(this);
                    dbManager.createClass(number);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
