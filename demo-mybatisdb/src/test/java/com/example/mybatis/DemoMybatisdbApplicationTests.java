package com.example.mybatis;

import com.example.mybatis.entity.User;
import com.example.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMybatisdbApplicationTests {

	@Test
	public void contextLoads() {
		SqlSessionFactory sessionFactory = SqlSessionFactoryUtil.create();
		SqlSession session = sessionFactory.openSession();
        /*UserDao userDao = session.getMapper(UserDao.class);
        User usr = userDao.get(1);*/
		User usr = session.selectOne("com.example.mybatis.dao.UserDao.get", 1);
		System.out.printf(usr.getName());
	}

}
