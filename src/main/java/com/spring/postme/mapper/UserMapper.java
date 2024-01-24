package com.spring.postme.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spring.postme.model.User;

@Mapper
public interface UserMapper {

	User getUserbyUsernameAndPassword(@Param("username")String username,@Param("password") String password);

	int insertUser(User newUser);
	

}