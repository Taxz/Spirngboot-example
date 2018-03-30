package edu.example.study.controller;

import edu.example.study.entity.User;
import edu.example.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Taxz on 2018/3/30.
 * RESTful风格
 */
@RestController
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/api/find/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable int id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/api/save",method = RequestMethod.POST)
    public User save(@ModelAttribute User user) {
        return service.save(user);
    }

    @RequestMapping(value = "/api/delete/{id}",method = RequestMethod.DELETE)
    public int delet(@PathVariable int id) {
        return service.delete(id);
    }
}
