package edu.example.study;

import edu.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.example.study.dao")
public class DemoSpringbootRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootRedisApplication.class, args);
	}
}
