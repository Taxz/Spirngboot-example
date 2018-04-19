package edu.example.study.configs;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taxz on 2018/4/18.
 */
@Configuration
public class DataSourceConfig {

    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource master(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("slave1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave-alpha")
    public DataSource slave1(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("slave2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave-beta")
    public DataSource slave2(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("slave3")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave-gamma")
    public DataSource slave3(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dataSource")
    public DataSource dataSource(){
        DataSourceRoutingDataSource dataSource = new DataSourceRoutingDataSource();
        Map<Object,Object> map = new HashMap();
        map.put(DataSourceKeys.master.name(), master());
        map.put(DataSourceKeys.slave1.name(), slave1());
        map.put(DataSourceKeys.slave2.name(), slave2());
        map.put(DataSourceKeys.slave3.name(), slave3());

        //set datasource as default
        dataSource.setDefaultTargetDataSource(master());
        //set master and slave as DataSource
        dataSource.setTargetDataSources(map);
        DynamicDataSourceContextHolder.lsit.addAll(map.keySet());
        DynamicDataSourceContextHolder.slaveNode.addAll(map.keySet());
        DynamicDataSourceContextHolder.slaveNode.remove(DataSourceKeys.master.name());
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean getSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager getTransactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}
