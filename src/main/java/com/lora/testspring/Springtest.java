package com.lora.testspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Springtest {
	 public static void main(String[] args){
	        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");//读取applicationcontext.xml中的内容
	        Person p = ctx.getBean("person",Person.class);//创建bean的引用对象
	        p.info();
	    }
}
