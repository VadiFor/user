package com.learn.java.service.impl;

import com.learn.java.dto.UserCreateRequestDto;
import com.learn.java.dto.UserUpdateRequestDto;
import com.learn.java.exception.IncorrectDataException;
import com.learn.java.mapper.UserMapper;
import com.learn.java.model.User;
import com.learn.java.repository.UserRepository;
import com.learn.java.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Override
	public User create(UserCreateRequestDto userCreateRequestDto) {
		checkExistsEmailOrPhone(userCreateRequestDto.getEmail(), userCreateRequestDto.getPhone());
		User newUser = userMapper.toUser(userCreateRequestDto);
		userRepository.save(newUser);
		return newUser;
	}
	
	@Override
	public User update(String id, UserUpdateRequestDto userUpdateRequestDto) {
		User foundUser = getUser(id);
		checkExistsEmailOrPhone(userUpdateRequestDto.getEmail(), userUpdateRequestDto.getPhone());
		userMapper.updateUserFromDto(userUpdateRequestDto, foundUser);
		User updatedUser = userRepository.save(foundUser);
		return updatedUser;
	}
	
	@Override
	public String delete(String id) {
		User foundUser = getUser(id);
		userRepository.delete(foundUser);
		return "User has been successfully deleted";
	}
	
	@Override
	public User getUser(String id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id «" + id + "» not found"));
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	private void checkExistsEmailOrPhone(String email, String phone) {
		StringBuilder exMessage = new StringBuilder();
		if (email != null && userRepository.existsByEmail(email))
			exMessage.append("User with the email «" + email + "» already exists\n");
		if (phone != null && userRepository.existsByPhone(phone))
			exMessage.append("User with the phone «" + phone + "» already exists\n");
		if (exMessage.length() > 0)
			throw new IncorrectDataException(exMessage.toString());
	}
	
	@Override
	public void checkRequestData(String firstName, String lastName, String middleName, LocalDate dateOfBirth) {
		StringBuilder exMessage = new StringBuilder();
		if (firstName != null && firstName.split(" ").length > 1)
			exMessage.append("FirstName must consist of one word\n");
		if (lastName != null && (lastName.split(" ").length > 1 || lastName.split("-").length > 2))
			exMessage.append("LastName must consist of one word or two hyphenated words\n");
		if (middleName != null && middleName.split(" ").length > 1)
			exMessage.append("MiddleName must consist of one word\n");
		if (dateOfBirth != null && LocalDate.now().getYear() - dateOfBirth.getYear() < 18)
			exMessage.append("User must be 18 years old or older\n");
		if (exMessage.length() > 0)
			throw new IncorrectDataException(exMessage.toString());
	}
	
	@Override
	public String normalizePhone(String phone) {
		if (phone != null && !phone.startsWith("+")) phone = "+" + phone.trim();
		return phone;
	}
}
