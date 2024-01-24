package com.spring.postme.service.user;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.postme.mapper.UserMapper;
import com.spring.postme.model.User;
import com.spring.postme.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserServiceImpl {
	
	@Autowired
	 UserMapper mapper;
	
	public User getUserbyUsernameAndPassword(String username, String password)throws SQLException {
		return mapper.getUserbyUsernameAndPassword(username, password);
	}

	public boolean insertUser(User newUser) throws Exception {
		boolean result = false;
		
		int res = mapper.insertUser(newUser);
		
		if(res !=0) {
			result = true;
		}else {
			new Exception("회원가입 실패");
		}
		return result;
	}

}
