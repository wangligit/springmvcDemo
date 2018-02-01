package com.lora.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.lora.dao.PersonRowMapper;
import com.lora.dao.PersonDao;
import com.lora.model.PersonBean;

public class PersonDaoImpl implements PersonDao {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate; 
      
    public void addPerson(PersonBean person) { 
        /**
         * 第一个参数为执行sql
         * 第二个参数为参数数据
         * 第三个参数为参数类型
         */ 
        jdbcTemplate.update("insert into person values(null,?)", new Object[]{person.getName()}, new int[]{Types.VARCHAR}); 
    } 
  
    public void deletePerson(int id) { 
        jdbcTemplate.update("delete from person where id = ?", new Object[]{id}, new int[]{Types.INTEGER}); 
    } 
  
    @SuppressWarnings("unchecked") 
    public PersonBean queryPerson(int id) { 
        /**
         * new PersonRowMapper()是一个实现RowMapper接口的类,
         * 执行回调,实现mapRow()方法将rs对象转换成PersonBean对象返回
         */ 
        List<PersonBean> pbs = (List<PersonBean>)jdbcTemplate.query("select id,name from person where id = ?", new Object[]{id}, new PersonRowMapper()); 
        PersonBean pb = null; 
        if(pbs.size()>0) { 
            pb = pbs.get(0); 
        } 
        return pb; 
    }  
  
    @SuppressWarnings("unchecked") 
    public List<PersonBean> queryPersons() { 
        List<PersonBean> pbs = (List<PersonBean>) jdbcTemplate.query("select id,name from person", new PersonRowMapper()); 
        return pbs; 
    } 
  
    public void updatePerson(PersonBean person) { 
        jdbcTemplate.update("update person set name = ? where id = ?", new Object[]{person.getName(), person.getId()}, new int[]{Types.VARCHAR, Types.INTEGER}); 
    } 

}
