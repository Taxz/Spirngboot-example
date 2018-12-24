package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Taxz on 2018/12/24.
 */

@Slf4j
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Transactional(value="test1TransactionManager",rollbackFor = Exception.class,timeout=36000)
    public void update(User user) {
        try {
            userMapper.update(user);
            log.info("update:user,{}", user);
        } catch (Exception e) {
            log.error("update occur exception:{}",e);
            throw  e;
        }
    }

}
