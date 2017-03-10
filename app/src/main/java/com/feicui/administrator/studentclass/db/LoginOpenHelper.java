package com.feicui.administrator.studentclass.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.feicui.administrator.studentclass.db.LoginEntry;

/**
 * Created by Administrator on 2016/9/5.
 * 登录数据库帮助类
 */
public class LoginOpenHelper extends SQLiteOpenHelper {
    //数据库版本号
    public static final int DATABASE_VERSION=1;
    //数据库名称
    public static final String DATABASE_NAME="student.db";

    public LoginOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 创建表
     * @param sqLiteDatabase  数据库
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LoginEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //删除原表
        sqLiteDatabase.execSQL(LoginEntry.SQL_DELETE_TABLE);
        //建表
        onCreate(sqLiteDatabase);
    }
}
