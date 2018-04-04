package edu.example.study.config;

import edu.example.study.interceptor.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by Taxz on 2018/4/3.
 */
@Configuration
public class MybatisInterceptor {
    @Bean
    public PageInterceptor getPageInterceptor(){
        Properties properties = new Properties();
        properties.setProperty("databaseType","mysql");
        PageInterceptor pg = new PageInterceptor();
        pg.setProperties(properties);
        return pg;
    }
}
