package com.spring.postme.service.user;

import java.util.List;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.postme.mapper.UserMapper;
import com.spring.postme.model.User;
import com.spring.postme.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;


@Service
public class UserService implements UserServiceImpl {

	private final UserMapper userMapper;

	@Autowired
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public User getUserbyUsernameAndPassword(String username, String password) throws SQLException {
		return userMapper.getUserbyUsernameAndPassword(username, password);
	}

	public boolean insertUsers(User newUser) throws Exception {
		boolean result = false;

		int res = userMapper.insertUsers(newUser);

		if (res != 0) {
			result = true;
		} else {
			new Exception("회원가입 실패");
		}
		return result;
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public User findById(Integer id) {
		return userMapper.findById(id);
	}

	@Override
	public void saveUser(User user) {
		if (user.getId() == null) {
			userMapper.insertUser(user);
		} else {
			userMapper.updateUser(user);
		}
	}

	@Override
	public void updateUser(Integer id, User user) {
		user.setId(id);
		userMapper.updateUser(user);
	}

	@Override
	public void deleteUser(Integer id) {
		userMapper.deleteById(id);
	}
}
