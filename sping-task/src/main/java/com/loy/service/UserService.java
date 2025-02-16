package com.loy.service;

import com.loy.dao.UserDao;
import com.loy.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(User user) {
        return userDao.createUser(user).orElseThrow(() -> new RuntimeException(user + " is not created"));
    }

    public User getUser(long id) {
        return userDao.getUser(id).orElseThrow(() -> new RuntimeException("User with id: " + id + " not found"));
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User updateUser(User user) {
        return userDao.updateUser(user).orElseThrow(() -> new RuntimeException("User with id: " + user.getId() + " not updated"));
    }

    public User deleteUser(User user) {
        return userDao.deleteUser(user).orElseThrow(() -> new RuntimeException("User with id: " + user.getId() + " not deleted"));
    }

}
