package edu.example.study.dao;

import edu.example.study.entity.person;
import edu.example.study.utill.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/28.
 */
@Repository
public interface PersonDao{
    //通过Id查找
    person findPersonById(@Param("id")int id);

    //更新
    int updatePerson(person p);

    //保存
    int savePerson(person p);

    //删除
    int delPerson(int p);

    List<person> queryByown(String sql);

    List<Map> excuteSql(Map map);

    List<person> queryPage(Page page);

}
