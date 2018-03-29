package edu.example.study.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/28.
 */
public class person implements Serializable {
    private static final long serialVersionUID = -1L;

    //年龄
    private int age;

    //ID
    private  int id;

    //姓名
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "person{" +
                "age=" + age +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
