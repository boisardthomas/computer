package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public enum LogDAO {

	INSTANCE;
	
	public void addLog( String operation, String type) throws SQLException
	{
		Connection cn = null;
		PreparedStatement st  = null;
		
		cn = ComputerDatabase.getInstance().getConnection();
		st = cn.prepareStatement("insert into log values (default,?,NOW(),?)");
		st.setString(1, operation);
		st.setString(2, type);
		st.executeUpdate();
		
		
		st.close();
		
	}
}
