package com.learn.java.service;

import com.learn.java.dto.UserCreateRequestDto;
import com.learn.java.dto.UserUpdateRequestDto;
import com.learn.java.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
	User create(UserCreateRequestDto userCreateRequestDto);
	User update(String id, UserUpdateRequestDto userUpdateRequestDto);
	String delete(String id);
	User getUser(String id);
	List<User> getAllUsers();
	void checkRequestData(String firstName, String lastName, String middleName, LocalDate dateOfBirth);
	String normalizePhone(String phone);
}
