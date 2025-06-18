package com.learn.java.controller;

import com.learn.java.model.User;
import com.learn.java.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usr")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable String id) {
		return userService.getUser(id);
	}
	
	@PostMapping
	public User createUser(@RequestParam(value = "name") String name,
						   @RequestParam(value = "email") String email,
						   @RequestParam(value = "phone", required = false) String phone) {
		if (phone != null && phone.charAt(0) == ' ') phone = "+" + phone.trim();
		return userService.create(name, email, phone);
	}
	
	@PatchMapping("/{id}")
	public User updateUser(@PathVariable String id,
						   @RequestParam(value = "name", required = false) String name,
						   @RequestParam(value = "email", required = false) String email,
						   @RequestParam(value = "phone", required = false) String phone) {
		return userService.update(id, name, email, phone);
	}
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable String id) {
		return userService.delete(id);
	}
}
