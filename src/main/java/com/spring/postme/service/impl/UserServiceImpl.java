package com.spring.postme.service.impl;

import com.spring.postme.model.User;
import java.util.List;

public interface UserServiceImpl {

    List<User> findAll();

    User findById(Integer id);

    void saveUser(User user);

    void updateUser(Integer id, User user);

    void deleteUser(Integer id);
}
