package edu.example.study.controller;

import edu.example.study.entity.person;
import edu.example.study.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/3/28.
 */
@RestController
public class PersonControl {

    @Autowired
    PersonService personService;

    //RESTful GET方式Select
    @RequestMapping(value = "/api/pso/{id}",method = RequestMethod.GET)
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
}
