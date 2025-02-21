package com.loy.service;

import com.loy.dao.UserDao;
import com.loy.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public User createUser(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("When user create id must be null");
        }
        return userDao.save(user);
    }

    public User getUser(long id) {
        return userDao.findById(id).orElseThrow(() -> new RuntimeException("User with id: " + id + " not found"));
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User updateUser(User user) {
        if(user.getId() == null) {
            throw new IllegalArgumentException("When user create id must not be null");
        }
        return userDao.save(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

}
