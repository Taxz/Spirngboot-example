package com.example.mq.util;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Queue;


/**
 * Created by Taxz on 2018/8/21.
 */
@Service("producer")
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;
    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     * @param destination
     * @param message
     */
    public void sendMessage(Destination destination, final String message) {
        jmsTemplate.convertAndSend(destination, message);
    }
    /**
     * 发送消息，message是待发送的消息
     * @param message
     */
    public void sendMessage(final String message) {
        jmsTemplate.convertAndSend("star.queue",message);
    }

}
