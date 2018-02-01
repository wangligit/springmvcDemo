package com.lora.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lora.dao.IUserDAO;
import com.lora.model.User;
import com.lora.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource(name="userDAO")
	private IUserDAO userDao;
	
	public void addUser(User user) {
		
		userDao.addUser(user);
	}

    public List<User> findAll() {
        
        return userDao.findAll();
    }

}
