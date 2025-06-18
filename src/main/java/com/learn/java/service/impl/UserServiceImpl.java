package com.learn.java.service.impl;

import com.learn.java.exception.DuplicateResourceException;
import com.learn.java.model.User;
import com.learn.java.repository.UserRepository;
import com.learn.java.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	@Override
	public User create(String name, String email, String phone) {
		checkExistsEmail(email);
		checkExistsPhone(phone);
		User newUser = User.builder()
				.name(name)
				.email(email)
				.phone(phone)
				.build();
		userRepository.save(newUser);
		return newUser;
	}
	
	@Override
	public User update(String id, String name, String email, String phone) {
		User foundUser = getUser(id);
		checkExistsEmail(email);
		checkExistsPhone(phone);
		if(name != null) foundUser.setName(name);
		if(email != null) foundUser.setEmail(email);
		if(phone != null) foundUser.setPhone(phone);
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
	
	private void checkExistsEmail(String email) {
		if(email != null && userRepository.existsByEmail(email))
			throw new DuplicateResourceException("User with the email «" + email + "» already exists");
	}
	
	private void checkExistsPhone(String phone) {
		if(phone != null && userRepository.existsByPhone(phone))
			throw new DuplicateResourceException("User with the phone «" + phone + "» already exists");
	}
}
