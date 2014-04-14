package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.jdbc.ComputerDatabase;

@Repository
public class LogDAO {

	@Autowired
	private ComputerDatabase computerDatabase;
	
	@Autowired
	private DataSource ds;
	
	public void addLog( String operation, String type) throws SQLException
	{
		Connection cn = null;
		PreparedStatement st  = null;
		
		cn = DataSourceUtils.getConnection(ds);
		st = cn.prepareStatement("insert into log values (default,?,NOW(),?)");
		st.setString(1, operation);
		st.setString(2, type);
		st.executeUpdate();
				
		st.close();
	}
	
	public void setComputerDatabase(ComputerDatabase computerDatabase) {
		this.computerDatabase = computerDatabase;
	}
}
