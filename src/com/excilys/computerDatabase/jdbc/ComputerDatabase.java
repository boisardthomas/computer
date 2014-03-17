package com.excilys.computerDatabase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ComputerDatabase {

	private static ComputerDatabase computerDatabase;
	private static Connection cn;
	
	private ComputerDatabase()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public static Connection getInstance()
	{
		if(computerDatabase==null)
		{
			computerDatabase = new ComputerDatabase();
		}
		
		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull"
					,"thomas","thomas");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cn;
	}
	
	public static void close()
	{
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
