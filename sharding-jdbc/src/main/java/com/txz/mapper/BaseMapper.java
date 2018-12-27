package com.txz.mapper;

/**
 * Created by Taxz on 2018/12/25.
 */
import com.txz.entity.Order;
import com.txz.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Kane on 2018/1/17.
 */
public interface BaseMapper {

    List<User> getAll();

    void save(User user);

    void add(Order order);

    List<Order> getOrders();

    List<Map<String,String>> getDetails();


}