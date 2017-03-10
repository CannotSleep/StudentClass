package com.feicui.administrator.studentclass.entity;

/**
 * Created by Administrator on 2016/11/3.
 * 课程对象实体类
 */
public class MyClass {
    //课程名称
    private String name;
    //课程学分
    private String point;
    //教师姓名
    private String teacher;
    //课程属性
    private String property;
    //课程所开年级
    private String grade;
    //课程号
    private String number;
    //课程序号
    private String order;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public MyClass(){

    }

}
