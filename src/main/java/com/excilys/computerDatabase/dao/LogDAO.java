package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public class LogDAO {

	private static LogDAO ldao;
	
	private LogDAO()
	{
		
	}
	
	public static LogDAO getInstance()
	{
		if(ldao == null)
		{
			ldao = new LogDAO();
		}
		
		return ldao;
	}
	
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
