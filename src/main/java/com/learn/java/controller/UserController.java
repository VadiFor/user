package com.learn.java.controller;

import com.learn.java.dto.UserCreateRequestDto;
import com.learn.java.dto.UserUpdateRequestDto;
import com.learn.java.exception.IncorrectDataException;
import com.learn.java.model.User;
import com.learn.java.model.enums.PositionUser;
import com.learn.java.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usr")
@RequiredArgsConstructor
@Tag(name = "User Controller")
public class UserController {
	private final UserService userService;
	
	@GetMapping
	@Operation(summary = "Get all users")
	public List<User> getAllUsers(@RequestHeader(value = "Authorization") String idAuthor) {
		checkIsAdmin(idAuthor);
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get user by ID")
	public User getUser(@PathVariable String id,
						@RequestHeader(value = "Authorization") String idAuthor) {
		if (!idAuthor.equals(id)) checkIsAdmin(idAuthor);
		return userService.getById(id);
	}
	
	@PostMapping
	@Operation(summary = "Create user")
	public User createUser(@RequestBody @Valid UserCreateRequestDto userCreateRequestDto,
						   @RequestHeader(value = "Authorization") String idAuthor) {
		System.out.println(userCreateRequestDto);
		if (userService.getAllUsers().isEmpty()) {
			if ((userCreateRequestDto.getPosition() == null) || (userCreateRequestDto.getPosition() != PositionUser.ADMIN))
				throw new IncorrectDataException("The first user must be in the Admin position");
		}
		else {
			checkIsAdmin(idAuthor);
		}
		userService.checkRequestData(userCreateRequestDto.getFirstName(), userCreateRequestDto.getLastName(), userCreateRequestDto.getMiddleName(), userCreateRequestDto.getDateOfBirth());
		userCreateRequestDto.setPhone(userService.normalizePhone(userCreateRequestDto.getPhone()));
		return userService.create(userCreateRequestDto);
	}
	
	@PatchMapping("/{id}")
	@Operation(summary = "Update user")
	public User updateUser(@PathVariable String id,
						   @RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto,
						   @RequestHeader(value = "Authorization") String idAuthor) {
		if (!idAuthor.equals(id)) checkIsAdmin(idAuthor);
		userService.checkRequestData(userUpdateRequestDto.getFirstName(), userUpdateRequestDto.getLastName(), userUpdateRequestDto.getMiddleName(), userUpdateRequestDto.getDateOfBirth());
		userUpdateRequestDto.setPhone(userService.normalizePhone(userUpdateRequestDto.getPhone()));
		return userService.update(id, userUpdateRequestDto);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete user")
	public String deleteUser(@PathVariable String id,
							 @RequestHeader(value = "Authorization") String idAuthor) {
		checkIsAdmin(idAuthor);
		return userService.delete(id);
	}
	
	private void checkIsAdmin(String idAuthor) {
		User author = userService.getById(idAuthor);
		if (author.getPosition() != PositionUser.ADMIN) {
			throw new IncorrectDataException("Only the Administrator can perform this action. Authorization with id = «" + idAuthor + "» is not an Administrator");
		}
	}
}
