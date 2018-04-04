package edu.example.study.service.impl;

import edu.example.study.dao.PersonDao;
import edu.example.study.entity.person;
import edu.example.study.service.PersonService;
import edu.example.study.utill.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/3/28.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *1.判断redis中存在数据，有就返回
     *2.数据库中查询数据，并设置缓存，返回数据
     */
    @Override
    public person findPersonById(int id) {
        String key = "id_"+id;
        ValueOperations<String, person> opertion = redisTemplate.opsForValue();
        boolean flag = redisTemplate.hasKey(key);
        if (flag) {
            person p = opertion.get(key);
            System.out.println("从缓存中取值");
            return p;
        }
        person p = personDao.findPersonById(id);
        opertion.set(key, p, 10, TimeUnit.SECONDS);
        return p;
    }

    @Override
    public int savePerson(person p) {
        return personDao.savePerson(p);
    }

    /**
     * 1.redis中存在对应缓存，则删除
     * 2.修改数据
     * @param p 修改为的数据
     * @return
     */
    @Override
    public int updatePerson(person p) {
        int id = personDao.updatePerson(p);
        String key = "id_" + p.getId();
        boolean flag = redisTemplate.hasKey(key);
        if (flag) {
            redisTemplate.delete(key);
        }
        return id;
    }

    /**
     * 1.存在对应的缓存，则移除
     * 2.从数据库中删除数据
     * @param id 主键
     * @return
     */
    @Override
    public int deletePerson(int id) {
        int ids = personDao.delPerson(id);
        String key = "id_"+id;
        boolean flag = redisTemplate.hasKey(key);
        if (flag) {
            redisTemplate.delete(key);
        }
        return ids;
    }

    @Override
    public List<person> queryByown(String sql) {
        return personDao.queryByown(sql);
    }

    @Override
    public List<Map> excuteSql(Map map) {
        return personDao.excuteSql(map);
    }

    @Override
    public List<person> queryPage(Page page) {
        return personDao.queryPage(page);
    }
}
