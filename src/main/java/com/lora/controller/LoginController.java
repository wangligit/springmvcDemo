package com.lora.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class LoginController {
    
    private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/login")
    public ModelAndView greeting(HttpServletRequest request) {
         Map<String, Object> map = new HashMap<String, Object>();
         log.info(request.getRequestURL() + "-----" + request.getRequestURI());
         return new ModelAndView("login",map);
    }
	 
	@RequestMapping(value="/toLogin/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		//token.setRememberMe(true);
		System.out.println("为了验证登录用户而封装的token"+ 
		ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		//获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();  
		try {
			//在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查 
			//每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			//所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中
			currentUser.login(token);
		} catch (AuthenticationException e) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过");
	         //清除token
            token.clear();
            return "/login";
		}
		return "redirect:/greeting";
	}
}
