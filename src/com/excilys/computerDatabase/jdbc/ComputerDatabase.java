package com.excilys.computerDatabase.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class ComputerDatabase {

	private static ComputerDatabase computerDatabase;
	private static BoneCPConfig config = new BoneCPConfig();
	private static BoneCP connectionPool;
	private static ThreadLocal<Connection> threadConnection;
	
	private ComputerDatabase()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull"); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
			config.setUsername("thomas"); 
			config.setPassword("thomas");
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			connectionPool = new BoneCP(config);
			threadConnection = new ThreadLocal<Connection>();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public static ComputerDatabase getInstance()
	{
		if(computerDatabase==null)
		{
			computerDatabase = new ComputerDatabase();
		}
		
		return computerDatabase;
	}
	
	public Connection getConnection()
	{
		Connection cn =null;
				
		try {
			if(threadConnection.get()==null)
			{
				threadConnection.set(connectionPool.getConnection());
			}
			cn = threadConnection.get();
			cn.setAutoCommit(false);
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
	
	public static void close()
	{
		connectionPool.shutdown();
	}
	
	
}
