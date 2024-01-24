package com.spring.postme.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.postme.mapper.UserMapper;
import com.spring.postme.model.User;
import com.spring.postme.service.impl.UserServiceImpl;

@Service
public class UserService implements UserServiceImpl {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public boolean insertUser(User newUser) {
        try {
            userMapper.insertUser(newUser); // UserMapper를 통해 사용자 추가
            return true; // 성공적으로 추가됨
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 추가 실패
        }
    }

}
