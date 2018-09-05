package com.example.mq.web;

import com.example.mq.util.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Taxz on 2018/9/5.
 */
@Controller
@RequestMapping("/")
public class controll {

    @Autowired
    Producer producer;

    @RequestMapping("/test")
    @ResponseBody
    public void test() {
        producer.sendMessage(new ActiveMQQueue("haha") ,"111112222222212121121212");
    }
}
