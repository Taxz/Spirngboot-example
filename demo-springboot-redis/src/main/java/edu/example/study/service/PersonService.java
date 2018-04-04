package edu.example.study.service;

import edu.example.study.entity.person;
import edu.example.study.utill.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/28.
 */

public interface PersonService {
    //通过id查找
    person findPersonById(int id);

    //保存
    int savePerson(person p);

    //修改
    int updatePerson(person p);

    //删除
    int deletePerson(int id);

    List<person> queryByown(String sql);

    List<Map> excuteSql(Map map);

    List<person> queryPage(Page page);

}
