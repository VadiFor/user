package com.learn.java.service;

import com.learn.java.model.User;

import java.util.List;

public interface UserService {
	User create(String name, String email, String phone);
	User update(String id, String name, String email, String phone);
	String delete(String id);
	User getUser(String id);
	List<User> getAllUsers();
}
