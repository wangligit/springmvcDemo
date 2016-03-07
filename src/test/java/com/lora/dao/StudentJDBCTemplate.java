package com.lora.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.lora.model.Student;

public class StudentJDBCTemplate implements StudentDAO {

	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		 this.dataSource = dataSource;
	     this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(String name, Integer age) {
		 String SQL = "insert into student (name, age) values (?, ?)";
	     jdbcTemplateObject.update( SQL, name, age);
	     System.out.println("Created Record Name = " + name + " Age = " + age);
	      return;
	}

	@Override
	public Student getStudent(Integer id) {
	      String SQL = "select * from student where id = ?";
	      Student student = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{id}, new StudentMapper());
	      return student;
	}

	@Override
	public List<Student> listStudents() {
	      String SQL = "select * from student";
	      List <Student> students = jdbcTemplateObject.query(SQL, 
	                                new StudentMapper());
	      return students;
	}

	@Override
	public void delete(Integer id) {
	      String SQL = "delete from student where id = ?";
	      jdbcTemplateObject.update(SQL, id);
	      System.out.println("Deleted Record with ID = " + id );
	      return;
	}

	@Override
	public void update(Integer id, Integer age) {
		   String SQL = "update student set age = ? where id = ?";
		      jdbcTemplateObject.update(SQL, age, id);
		      System.out.println("Updated Record with ID = " + id );
		      return;
	}

}
