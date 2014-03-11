package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComputerDAO {

	public ArrayList<Computer> getListComputer()
	{
		ArrayList<Computer> computerArray = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull","thomas","thomas");
			
			Statement st = cn.createStatement();
			
			String req = "select cpt.name, cpt.introduced, cpt.discontinued, cpny.name from computer as cpt inner join company as cpny on cpt.company_id=cpny.id;";
			
			ResultSet rs = st.executeQuery(req);
			
			while(rs.next())
			{
				Computer c = new Computer (rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				computerArray.add(c);
			}
			
			st.close();
			rs.close();
			cn.close();			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return computerArray;
	}
	
	public void addComputer(String name, String intro, String disc, int company)
	{
		
	}

}
