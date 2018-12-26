package com.txz.entity;

import com.txz.enums.UserSexEnum;
import lombok.Data;

/**
 * Created by Taxz on 2018/12/25.
 */

@Data
public class UserEntity {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long order_id;
    private Long user_id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;

}
