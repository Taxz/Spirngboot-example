package com.example.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.java2d.cmm.Profile;

import javax.jms.Queue;

@SpringBootApplication
public class DemoActivemqApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoActivemqApplication.class, args);
	}
}
