package edu.example.study.entity;


import java.io.Serializable;

/**
 * Created by Taxz on 2018/3/29.
 *
 */
public class User implements Serializable {
    private static final long serialVersionUID = -8583230364880438567L;

    private int id ;

    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
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
}
