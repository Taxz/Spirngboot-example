package edu.example.study;

import edu.example.study.entity.person;
import edu.example.study.service.PersonService;
import edu.example.study.utill.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringbootRedisApplicationTests {
	@Autowired
	PersonService personService;
	@Test
	public void contextLoads() {
		Page<person> page = new Page<>();
		page.setPageNo(2);
		List<person> pes = personService.queryPage(page);
		System.out.println(pes);


	}

}
