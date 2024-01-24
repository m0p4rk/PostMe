package com.spring.postme.controller.user;

import com.spring.postme.model.User;
import com.spring.postme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// 모든 사용자 조회
	@GetMapping
	public List<User> getAllUsers() {
		return userService.findAll();
	}

	// ID로 사용자 조회
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Integer id) {
		return userService.findById(id);
	}

	// 사용자 추가
	@PostMapping
	public void addUser(@RequestBody User user) {
		userService.saveUser(user);
	}

	// 사용자 정보 업데이트
	@PutMapping("/{id}")
	public void updateUser(@PathVariable Integer id, @RequestBody User user) {
		userService.updateUser(id, user);
	}

	// ID로 사용자 삭제
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}
}
