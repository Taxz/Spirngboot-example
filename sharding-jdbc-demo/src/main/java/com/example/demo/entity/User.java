package com.example.demo.entity;

import com.example.demo.utill.SexEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Taxz on 2018/12/24.
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1234L;

    private Long id;
    private Long order_id;
    private Long user_id;
    private String userName;
    private String passWord;
    private SexEnum userSex;
    private String nickName;

}
