package com.lora.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lora.model.User;
import com.lora.service.IUserService;

@Controller
public class UserController {
	
	@Resource(name="userService")
	private IUserService userService;

	 @RequestMapping("/")
	 public String index(HttpServletRequest req,HttpServletResponse resp,ModelMap model) {
	         Map<String, Object> map = new HashMap<String, Object>();
	         return "/hello";
	    }
	 
	 @RequestMapping("/addUser")
	 public String addUser(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		 User user = new User();
		 user.setUname("lora");
		 user.setPwd("123456");
		 userService.addUser(user);
		 model.put("User", user);
		 return "/hello";
	 }
}
