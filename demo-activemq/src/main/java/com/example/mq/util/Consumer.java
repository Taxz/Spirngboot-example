package com.example.mq.util;

import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by Taxz on 2018/8/21.
 */

@Component
@PropertySource("classpath:activeMq.properties")
public class Consumer {

    @JmsListener(destination ="haha", containerFactory = "jmsQueueListener")
    public void receiveQueue(final TextMessage text, Session session)
            throws JMSException {
        try {
            System.out.println("接收到消息:"+text.getText());
            text.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
        } catch (Exception e) {
            session.recover();// 此不可省略 重发信息使用
        }
    }
}
