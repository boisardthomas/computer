package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	public void addLog(Connection cndb, String operation)
	{
		Connection cn = null;
		PreparedStatement st  = null;
		try
		{
			cn = cndb;
			st = cn.prepareStatement("insert into log (default,?,?)");
			st.setString(1, operation);
			st.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			st.executeUpdate();
		}
		catch(SQLException e)
		{
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally
		{
			try {
				st.close();
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
