package edu.example.study.service;

import edu.example.study.entity.User;
import edu.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.parser.PartTree;
import org.springframework.stereotype.Service;

/**
 * Created by Taxz on 2018/3/30.
 */
@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public User findById(int id) {
        return userRepository.findUserById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public int delete(int id) {
        return userRepository.deleteById(id);
    }
}
