package com.lora.service;

import java.util.List;

import com.lora.model.User;

public interface IUserService {

	public void addUser(User user);
	
	public List<User> findAll();
}
