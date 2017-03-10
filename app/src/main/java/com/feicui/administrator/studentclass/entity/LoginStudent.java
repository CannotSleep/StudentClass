package com.feicui.administrator.studentclass.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/3.
 * 学生对象的实体类
 */
public class LoginStudent implements Serializable{
    //学号
    private String Number;
    //密码
    private String Password;
    //姓名
    private String name;
    //入学年份
    private String beginyear;
    //出生日期
    private String born;
    //父母
    private String parent;
    //民族
    private String nation;
    //性别
    private String sex;
    //宿舍
    private String room;
    //家庭住址
    private String family;
    //联系方式
    private String phone;
    //身份证
    private String idnum;
    //毕业学校
    private String hignschool;
    //录取批次
    private String pici;
    //政治面貌
    private String politics;
    //qq
    private String qq;

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getPici() {
        return pici;
    }

    public void setPici(String pici) {
        this.pici = pici;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getHignschool() {
        return hignschool;
    }

    public void setHignschool(String hignschool) {
        this.hignschool = hignschool;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public LoginStudent() {

    }

    public LoginStudent(String name, String beginyear, String number, String password) {
        this.name = name;
        this.beginyear = beginyear;
        Number = number;
        Password = password;
    }

    public String getBeginyear() {
        return beginyear;
    }

    public void setBeginyear(String beginyear) {
        this.beginyear = beginyear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
