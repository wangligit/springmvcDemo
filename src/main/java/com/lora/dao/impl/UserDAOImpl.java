package com.lora.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.lora.dao.IUserDAO;
import com.lora.model.User;

@Repository("userDAO")
public class UserDAOImpl extends JdbcDaoSupport implements IUserDAO {
	
	@Resource
	private JdbcTemplate template;
	
	@Resource
	public void setTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}

	@Override
	public void addUser(User user) {
		
		  String sql = "insert into t_user(username, password) values(?,?)";
		  template.update(sql, user.getUname(),
	                user.getPwd());
	}

	@Override
	public void deleteUser(int id) {
		
		  String sql = "delete from t_user where id=?";
	      this.getJdbcTemplate().update(sql, id);
	}

	@Override
	public void updateUser(User user) {

		   String sql = "update t_user set username=?,password=? where id=?";
	        this.getJdbcTemplate().update(sql, user.getUname(),
	                user.getPwd(), user.getId());
	}

	/* 
	 * 简单查询，按照ID查询，返回字符串
	 */
	@Override
	public String searchUserName(int id) {

		 String sql = "select username from t_user where id=?";
	        // 返回类型为String(String.class)
	     return this.getJdbcTemplate().queryForObject(sql, String.class, id);
	}

	@Override
    public User searchUser(int id) {
        String sql="select * from t_user where id=?";
        return this.getJdbcTemplate().queryForObject(sql, new UserRowMapper(), id);
    }

	@Override
    public List<User> findAll() {// 复杂查询返回List集合
        String sql = "select * from t_user";
        return this.getJdbcTemplate().query(sql, new UserRowMapper());

    }
	
	class UserRowMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		    User user = new User();
            user.setId(rs.getInt("id"));
            user.setUname(rs.getString("username"));
            user.setPwd(rs.getString("password"));
            return user;
		}
		
	} 

}	
