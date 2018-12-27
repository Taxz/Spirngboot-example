package com.txz.service;

import com.txz.entity.Order;
import com.txz.entity.User;
import com.txz.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Taxz on 2018/12/25.
 */
@Slf4j
@Service
public class BaseService {

    @Autowired
    private BaseMapper mapper;

    public List<User> getUsers() {
        List<User> users= mapper.getAll();
        return users;
    }

    @Transactional(value="shardTransactionManager",rollbackFor = Exception.class,timeout=36000)  //说明针对Exception异常也进行回滚，如果不标注，则Spring 默认只有抛出 RuntimeException才会回滚事务
    public void updateTransactional(User user) {
        try{
            mapper.save(user);
            //log.error(String.valueOf(user));
        }catch(Exception e){
            log.error("find exception!");
            throw e;   // 事物方法中，如果使用trycatch捕获异常后，需要将异常抛出，否则事物不回滚。
        }

    }

    @Transactional(value="shardTransactionManager",rollbackFor = Exception.class,timeout=36000)
    public void save(Order order) {
        mapper.add(order);
    }

    public List<Map<String, String>> getDetails() {
        return mapper.getDetails();
    }
}