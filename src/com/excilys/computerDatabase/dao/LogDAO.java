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
	
	public void addLog(Connection cndb, String operation, String type)
	{
		Connection cn = null;
		PreparedStatement st  = null;
		try
		{
			cn = cndb;
			st = cn.prepareStatement("insert into log values (default,?,NOW(),?)");
			st.setString(1, operation);
			st.setString(2, type);
			st.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
