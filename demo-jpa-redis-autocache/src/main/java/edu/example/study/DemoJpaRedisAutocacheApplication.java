package edu.example.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoJpaRedisAutocacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaRedisAutocacheApplication.class, args);
	}
}
