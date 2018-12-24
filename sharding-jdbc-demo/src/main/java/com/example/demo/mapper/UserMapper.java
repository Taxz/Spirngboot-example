package com.example.demo.mapper;

import com.example.demo.entity.User;

import java.util.List;

/**
 * Created by Taxz on 2018/12/24.
 */

public interface UserMapper {
    List<User> getAll();

    void update(User user);
}
