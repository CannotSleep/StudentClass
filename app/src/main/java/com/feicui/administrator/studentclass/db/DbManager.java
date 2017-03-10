package com.feicui.administrator.studentclass.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.feicui.administrator.studentclass.entity.LoginStudent;
import com.feicui.administrator.studentclass.entity.MyClass;

import static android.provider.BaseColumns._ID;

/**
 * Created by Administrator on 2016/11/2.
 */
public class DbManager {
    private LoginOpenHelper op;

    public DbManager(Context context) {
        op = new LoginOpenHelper(context);
    }

    /**
     * 查询登陆信息表的方法
     */

    public LoginStudent searchLogin(String number) {
        SQLiteDatabase db=null;
        LoginStudent student=null;
        try {
            db=op.getReadableDatabase();
            Cursor c =db.query(
                    LoginEntry.TABLE_NAME,
                    new String[]{LoginEntry.COLUMNS_NAME_NAME,
                            LoginEntry.COLUMNS_BEGIN_YEAR,
                            LoginEntry.COLUMNS_NAME_NUMBER,
                            LoginEntry.COLUMNS_NAME_PASSWORD,
                            LoginEntry.COLUMNS_FAMILY,
                            LoginEntry.COLUMNS_BORN,
                            LoginEntry.COLUMNS_HIGHSCHOOL,
                            LoginEntry.COLUMNS_IDNUM,
                            LoginEntry.COLUMNS_NATION,
                            LoginEntry.COLUMNS_PARENT,
                            LoginEntry.COLUMNS_PHONE,
                            LoginEntry.COLUMNS_PICI,
                            LoginEntry.COLUMNS_POLITICS,
                            LoginEntry.COLUMNS_QQ,
                            LoginEntry.COLUMNS_ROOM,
                            LoginEntry.COLUMNS_SEX,
                    },
                    "Number = ?",
                    new String[]{number},
                    null,
                    null,
                    null
            );
            c.moveToFirst();
            do{
                String year=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_BEGIN_YEAR));
                String stnumber=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_NAME_NUMBER));
                String ps = c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_NAME_PASSWORD));
                String na=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_NAME_NAME));
                  //获得家庭地址
                String family=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_FAMILY));
                //获得出生日期
                String born =c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_BORN));
                //获得毕业学校
                String highschool=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_HIGHSCHOOL));
                //获得身份证号码
                String idnum=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_IDNUM));
                //获得民族
                String nation=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_NATION));
                //获得父母
                String parent=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_PARENT));
                //获得联系方式
                String conection=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_PHONE));
                //获得批次
                String pici=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_PICI));
                //获得政治面貌
                String politic=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_POLITICS));
                //获得qq
                String qq=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_QQ));
                //获得宿舍
                String room=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_ROOM));
                //获得性别
                String sex=c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_SEX));
                student=new LoginStudent();
                student.setName(na);
                student.setBeginyear(year);
                student.setNumber(stnumber);
                student.setPassword(ps);
                student.setBorn(born);
                student.setFamily(family);
                student.setHignschool(highschool);
                student.setIdnum(idnum);
                student.setNation(nation);
                student.setPhone(conection);
                student.setParent(parent);
                student.setPici(pici);
                student.setPolitics(politic);
                student.setQq(qq);
                student.setRoom(room);
                student.setSex(sex);
            }while (c.moveToNext());

        } catch (Exception e) {
        } finally {
                db.close();
        }
        return student;
    }
    /**
     * 修改密码的方法
     */
    public boolean changePass(String num,String ps){
        SQLiteDatabase db=null;
            db=op.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put("Password",ps);
            db.update(LoginEntry.TABLE_NAME,cv, "Number = ?",
                    new String[]{num});
        return true;
    }

    /**
     * 创建学生课表
     */
    public void createClass(String number){
        String str="create table if not exists  "+ "A"+number +"  ("
                +" id integer primary key,"+LoginEntry.COLUMNS_CLASS_NAME+" text,"+LoginEntry.COLUMNS_CLASS_NUMBER+" text,"
                +LoginEntry.COLUMNS_CLASS_POINT+" text,"+LoginEntry.COLUMNS_CLASS_PROPERTY+" text,"+LoginEntry.COLUMNS_CLASS_ORDER+" text"+" )";
        SQLiteDatabase db=null;
        db=op.getWritableDatabase();
        db.execSQL(str);
    }

    /**
     * 查询学生的课表
     */
    public boolean searchClass(String number,String classname){
        SQLiteDatabase db =null;
        db=op.getReadableDatabase();
        try {
            Cursor c = db.query(
                    "A"+number,
                    new String[]{LoginEntry.COLUMNS_CLASS_NAME},
                    null,
                    null,
                    null,
                    null,
                    null
            );
            c.moveToFirst();
            do{
                String name = c.getString(c.getColumnIndexOrThrow(LoginEntry.COLUMNS_CLASS_NAME));
                if(name.equals(classname)){
                    return  true;
                }
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){

        }finally {
            db.close();
        }
        return false;
    }

    /**
     * 学生选择课程
     */
    public  void chooseclass(String number, MyClass mc){
        SQLiteDatabase db = null;
        db=op.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LoginEntry.COLUMNS_CLASS_NAME,mc.getName());
        cv.put(LoginEntry.COLUMNS_CLASS_NUMBER,mc.getNumber());
        cv.put(LoginEntry.COLUMNS_CLASS_ORDER,mc.getOrder());
        cv.put(LoginEntry.COLUMNS_CLASS_POINT,mc.getPoint());
        cv.put(LoginEntry.COLUMNS_CLASS_PROPERTY,mc.getProperty());
        db.insert("A"+number,null,cv);
        db.close();
    }

    /**
     * 学生退出课程
     */
    public void cancleclass(String number,MyClass mc){
        SQLiteDatabase db =null;
        db=op.getWritableDatabase();
        db.delete("A"+number," name = ? ",new String[]{mc.getName()});
        db.close();
    }

}
