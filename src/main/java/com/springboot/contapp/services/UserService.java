package com.springboot.contapp.services;

import com.springboot.contapp.models.User;
import com.springboot.contapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
