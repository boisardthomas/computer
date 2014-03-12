package com.excilys.computerDatabase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ComputerDatabase {

	private static Connection cn;
	
	private static void initComputerDatabase()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull"
					,"thomas","thomas");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public static Connection getInstance()
	{
		if(cn==null)
			initComputerDatabase();
		return cn;
	}
	
}
