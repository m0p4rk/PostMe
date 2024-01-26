package com.spring.postme.mapper;

import com.spring.postme.model.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	User getUserByUsername(@Param("username") String username);

	void insertUser(User newUser);

	void insertAdmin(User newAdmin);

	int countUsers();

	int deleteAllUsers();

	void deleteUserById(@Param("userId") Integer userId);

	List<User> getUserList();

	User getUserById(@Param("userId") Integer userId);

	void updateUser(User user);

	void updateUserAdminStatus(@Param("userId") Integer userId, @Param("adminStatus") boolean adminStatus);
}
