package com.feicui.administrator.studentclass.db;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/9/5.
 * 登陆数据库契约类
 */
public class LoginEntry implements BaseColumns{
    //存放数据库的目录
    public  static final String DATABASE_PATH="/data/data/com.feicui.administrator.studentclass/databases";
    //表名
    public static final String TABLE_NAME="login";
    public static final String TABLE_CLASS="class";

    //学生列名
    public static final String COLUMNS_NAME_NUMBER="Number";
    public static final String COLUMNS_NAME_PASSWORD="Password";
    public static final String COLUMNS_NAME_NAME="name";
    public static final String COLUMNS_BEGIN_YEAR="BeginYear";
    public static final String COLUMNS_BORN="born";
    public static final String COLUMNS_PARENT="parent";
    public static final String COLUMNS_NATION="nation";
    public static final String COLUMNS_SEX="sex";
    public static final String COLUMNS_ROOM="room";
    public static final String COLUMNS_PHONE="conection";
    public static final String COLUMNS_FAMILY="family";
    public static final String COLUMNS_IDNUM="idnum";
    public static final String COLUMNS_HIGHSCHOOL="highschool";
    public static final String COLUMNS_PICI="pici";
    public static final String COLUMNS_POLITICS="politics";
    public static final String COLUMNS_QQ="qq";

    //课程列名
    public static final String COLUMNS_CLASS_NAME="name";
    public static final String COLUMNS_CLASS_NUMBER="number";
    public static final String COLUMNS_CLASS_POINT="point";
    public static final String COLUMNS_CLASS_PROPERTY="property";
    public static final String COLUMNS_CLASS_ORDER="ordered";


    //创建表格的SQL语句
    public static final String SQL_CREATE_TABLE="create table "+TABLE_NAME+" ("
            +_ID+" integer primary key,"+COLUMNS_NAME_NUMBER+" text,"+COLUMNS_NAME_PASSWORD+" text"
            +" )";
    //删除表格的SQL语句
    public static final String SQL_DELETE_TABLE="drop table if exists "+TABLE_NAME;
}
