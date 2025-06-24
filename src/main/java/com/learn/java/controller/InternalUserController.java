package com.learn.java.controller;

import com.learn.java.dto.UserInternalResponseDto;
import com.learn.java.mapper.UserMapper;
import com.learn.java.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/usr")
@RequiredArgsConstructor
@Hidden
public class InternalUserController {
	private final UserService userService;
	private final UserMapper userMapper;
	
	@GetMapping("/{id}")
	public UserInternalResponseDto getUser(@PathVariable String id) {
		return userMapper.toInternalDto(userService.getById(id));
	}
}
