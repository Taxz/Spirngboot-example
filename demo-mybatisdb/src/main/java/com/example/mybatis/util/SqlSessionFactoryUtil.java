package com.example.mybatis.util;

import com.example.mybatis.dao.ExecuteDao;
import com.example.mybatis.entity.User;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * Created by Taxz on 2018/4/16.
 */
public class SqlSessionFactoryUtil {
    public static SqlSessionFactory create(){
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dengji");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(ExecuteDao.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return sqlSessionFactory;
    }
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sessionFactory = create();
        SqlSession session = sessionFactory.openSession();
        ExecuteDao dao = session.getMapper(ExecuteDao.class);
        List<Map> usr = dao.get("select * from user ");
        /*User usr = session.selectOne("com.example.mybatis.dao.UserDao.get", 1);
        User usr = session.selectOne("select * from user where id = #{id}", 1);*/
        System.out.printf(usr.toString());


    }
    public static void fileCreate() throws IOException {
        /*Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(reader);
        reader.close();
        SqlSession session = null;
        session = sqlSessionFactory.openSession();
        UserDao dao =  session.getMapper(UserDao.class);
        User user = dao.get("");
        System.out.println(user.getName()+"查询结果");*/
    }
}
