package com.txz.web;

import com.txz.entity.Order;
import com.txz.entity.User;
import com.txz.enums.SexEnum;
import com.txz.service.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Taxz on 2018/12/25.
 */
@RestController
public class UserController {

    @Autowired
    private BaseService service;

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users= service.getUsers();
        return users;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @RequestMapping(value="addUser",method = RequestMethod.POST)
    public String updateTransactional(@RequestParam(value = "id") Long id,
                                      @RequestParam(value = "user_id") Long user_id,
                                      @RequestParam(value = "order_id") Long order_id,
                                      @RequestParam(value = "nickName") String nickName,
                                      @RequestParam(value = "passWord") String passWord,
                                      @RequestParam(value = "userName") String userName) {
        User user2 = new User();
        user2.setId(id);
        user2.setUser_id(user_id);
        user2.setOrder_id(order_id);
        user2.setNickName(nickName);
        user2.setPassWord(passWord);
        user2.setUserName(userName);
        user2.setUserSex(SexEnum.WOMAN);
        service.updateTransactional(user2);
        return "success";
    }

    @ApiOperation(value="创建Order", notes="创建Order")
    @RequestMapping(value="addOrder",method = RequestMethod.POST)
    public String saveOrder(@RequestParam(value = "id") Long id,
                            @RequestParam(value = "userId") Long userId,
                            @RequestParam(value = "orderId") Long orderId,
                            @RequestParam(value = "product") String product,
                            @RequestParam(value = "number") String number,
                            @RequestParam(value = "price") String price,
                            @RequestParam(value = "address") String address) {

        Order order = new Order();
        order.setAddress(address);
        order.setId(id);
        order.setNumber(number);
        order.setOrderId(orderId);
        order.setPrice(price);
        order.setProduct(product);
        order.setUserId(userId);
        service.save(order);
        return "success";
    }

    @ApiOperation(value="详情list", notes="获取Oder和user详细信息")
    @RequestMapping(value="getAll",method = RequestMethod.GET)
    public String getDetail() {
        return service.getDetails().toString();
    }
}