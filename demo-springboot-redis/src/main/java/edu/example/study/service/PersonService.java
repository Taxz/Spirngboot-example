package edu.example.study.service;

import edu.example.study.entity.person;
import org.springframework.stereotype.Component;

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


}
