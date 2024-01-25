package com.spring.postme.mapper;

import com.spring.postme.model.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User getUserByUsername(@Param("username") String username);

	void insertUser(User newUser);
	
	int countUsers();

    int deleteAllUsers();
    
	List<User> getUserList();

	User getUserById(@Param("userId") Integer userId);
	
	void updateUser(User user);

}
