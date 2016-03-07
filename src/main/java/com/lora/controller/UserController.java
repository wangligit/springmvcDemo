package com.lora.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	 @RequestMapping("/")
	 public String index(HttpServletRequest req,HttpServletResponse resp,ModelMap model) {
	         Map<String, Object> map = new HashMap<String, Object>();
	         return "/hello";
	    }
}
