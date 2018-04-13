package edu.example.study.controller;

import edu.example.study.entity.person;
import edu.example.study.service.PersonService;
import edu.example.study.utill.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/28.
 */
@RestController
public class PersonControl {

    @Autowired
    PersonService personService;

    //RESTful GET方式Select
    @RequestMapping(value = "/api/pso/{id}",method = RequestMethod.GET)
    @ResponseBody
    public person findPersonById(@PathVariable("id")int id){
        return personService.findPersonById(id);
    }

    //POST creat
    @RequestMapping(value = "/api/pso",method = RequestMethod.POST)
    public void createPerson(@RequestBody person p) {
        personService.savePerson(p);
    }

    //PUT update
    @RequestMapping(value = "/api/pso",method = RequestMethod.PUT)
    public void update(@RequestBody person p) {
        personService.updatePerson(p);
    }

    //DELETE delete
    @RequestMapping(value = "/api/pso/{id}",method = RequestMethod.DELETE)
    public void delPerson(@PathVariable("id") int id) {
        personService.deletePerson(id);
    }

    @RequestMapping(value = "/api/pso/add",method = RequestMethod.POST)
    public String getQuery(@RequestParam String sql){
        List<person> p = personService.queryByown(sql);
        return p.toString();
    }

    @RequestMapping(value = "/api/pso/excu",method = RequestMethod.POST)
    public String getExcu(String sql,String pagarm){
        sql="select * from person where age = #{age}";
        pagarm = "24";
        Map map = new HashMap<String,String>();
        map.put("sql",sql);
        map.put("age",pagarm);
        sql = "select count(0) from ("+sql+")";
        List<Map> maps = personService.excuteSql(map);
        return maps.toString();
    }
    @RequestMapping(value = "api/page",method = RequestMethod.GET)
    public String getPage(){
        Page<person> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(3);
        List<person> pes = personService.queryPage(page);
        System.out.println(pes);
        return pes.toString();
    }
}
