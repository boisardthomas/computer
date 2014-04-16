package com.excilys.computerDatabase.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDAO {

	@Autowired
	private DataSource ds;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addLog( String operation, String type) throws SQLException
	{
		String sql = "insert into log values (default,?,NOW(),?)";
		
				
		jdbcTemplate.update(sql, new Object[] { operation, type});
		
		
	}
	
}
