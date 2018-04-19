package edu.example.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//首先禁用自动配置数据源
@SpringBootApplication
//@MapperScan("edu.example.study.mapper")
public class DemoMybatisdatasourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMybatisdatasourcesApplication.class, args);
	}
}
