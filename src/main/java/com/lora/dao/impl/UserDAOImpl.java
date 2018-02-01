package com.lora.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lora.dao.IUserDAO;
import com.lora.model.User;

@Repository("userDAO")
public class UserDAOImpl implements IUserDAO {
	
    @Resource(name="jdbcTemplateAdmin")
    private JdbcTemplate jdbcTemplate;
	
	public void addUser(User user) {
		
		  String sql = "insert into t_user(username, password) values(?,?)";
		  jdbcTemplate.update(sql, user.getUname(),
	                user.getPwd());
	}

	public void deleteUser(int id) {
		
		  String sql = "delete from t_user where id=?";
		  jdbcTemplate.update(sql, id);
	}

	public void updateUser(User user) {

		   String sql = "update t_user set username=?,password=? where id=?";
	        this.jdbcTemplate.update(sql, user.getUname(),
	                user.getPwd(), user.getId());
	}

	/* 
	 * 简单查询，按照ID查询，返回字符串
	 */
	public String searchUserName(int id) {

		 String sql = "select username from t_user where id=?";
	        // 返回类型为String(String.class)
	     return this.jdbcTemplate.queryForObject(sql, String.class, id);
	}

	public User searchUser(int id) {
        String sql="select * from t_user where id=?";
        return this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

	public List<User> findAll() {// 复杂查询返回List集合
        String sql = "select * from t_user";
        return this.jdbcTemplate.query(sql, new UserRowMapper());

    }
	
	class UserRowMapper implements RowMapper<User>{

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		    User user = new User();
            user.setId(rs.getInt("id"));
            user.setUname(rs.getString("username"));
            user.setPwd(rs.getString("password"));
            return user;
		}
		
	} 

}	
