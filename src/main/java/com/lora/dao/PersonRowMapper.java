package com.lora.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lora.model.PersonBean;

public class PersonRowMapper implements RowMapper<PersonBean> {
	 //默认已经执行rs.next(),可以直接取数据 
    public PersonBean mapRow(ResultSet rs, int index) throws SQLException { 
        PersonBean pb = new PersonBean(rs.getInt("id"),rs.getString("name")); 
        return pb; 
        
    } 
}
