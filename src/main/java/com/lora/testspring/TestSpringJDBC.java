package com.lora.testspring;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lora.dao.IUserDAO;
import com.lora.model.User;



public class TestSpringJDBC {

    @Test
    public void addUser(){
        User user=new User();
        user.setId(3);
        user.setUname("admin");
        user.setPwd("123456");
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Beans.xml");
        IUserDAO dao=(IUserDAO) applicationContext.getBean("userDao");
        //IUserDAO dao = new UserDAOImpl();
        dao.addUser(user);
    }
    
    @Test
    public void updateUser(){
        User user=new User();
        user.setId(3);
        user.setUname("admin");
        user.setPwd("admin");
        
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Beans.xml");
        IUserDAO dao=(IUserDAO) applicationContext.getBean("userDao");
        dao.updateUser(user);
    }
    
    @Test
    public void deleteUser(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Beans.xml");
        IUserDAO dao=(IUserDAO) applicationContext.getBean("userDao");
        dao.deleteUser(3);
    }
    
    @Test
    public void findById(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Beans.xml");
        IUserDAO dao=(IUserDAO) applicationContext.getBean("userDao");
        String name=dao.searchUserName(1);
        System.out.println(name);
    }
    
    @Test
    public void findUserById(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Beans.xml");
        IUserDAO dao=(IUserDAO) applicationContext.getBean("userDao");
        User user=dao.searchUser(1);
        System.out.println(user.getUname());
    }
    
    @Test
    public void findAll(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Beans.xml");
        IUserDAO dao=(IUserDAO) applicationContext.getBean("userDao");
        List<User> users=dao.findAll();
        System.out.println(users.size());
    }
	

}
