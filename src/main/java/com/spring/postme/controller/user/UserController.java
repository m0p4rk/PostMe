package com.spring.postme.controller.user;

import com.spring.postme.model.User;
import com.spring.postme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// 로그인

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		String view = "error";
		User user = null;
		try {
			user = userService.getUserbyUsernameAndPassword(username, password);
			System.out.println(user);

			if (user != null)
				session.setAttribute("user", user.getUsername());
			session.setAttribute("user", user.getId());

			view = "redirect:/main";
			return view;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		if (session != null) {
			session.invalidate();
		}
		return "redirect:/main";
	}

	// 회원가입
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String insertUser(@ModelAttribute User newUser) {
		String view = "error";
		boolean result = false;
		try {
			result = userService.insertUsers(newUser);
			System.out.println(result);

			if (result) {
				view = "redirect:/main";
				return view;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return view;
		}

		return view;
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
