package com.excilys.computerDatabase.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

@Component
public class ComputerDatabase {
	
	private ThreadLocal<Connection> threadConnection;
	@Autowired
	DataSource ds;
	
	public ComputerDatabase()
	{
		threadConnection = new ThreadLocal<Connection>();
	}
	
	
	public Connection getConnection()
	{
		Connection cn =null;
				
		try {
			if(threadConnection.get()==null)
			{
				threadConnection.set(ds.getConnection());
			}
			cn = threadConnection.get();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cn;
	}
	
	public void closeConnection()
	{
		try {
			threadConnection.get().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadConnection.set(null);
	}
	

	
}
