package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public class ComputerDAO {

	public ArrayList<Computer> getListComputer()
	{
		ArrayList<Computer> computerArray = new ArrayList<>();
		
		try {
			Connection cn = ComputerDatabase.getInstance();
						
			String req = "select cpt.name, cpt.introduced, cpt.discontinued, cpny.name from computer as cpt left outer inner join company as cpny on cpt.company_id=cpny.id;";
			
			Statement st = cn.prepareStatement(req);
			
			ResultSet rs = st.executeQuery(req);
			
			while(rs.next())
			{
				Computer c = new Computer (rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				computerArray.add(c);
			}
			
			st.close();
			rs.close();
			cn.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return computerArray;
	}
	
	public void addComputer(String name, String intro, String disc, int company)
	{
		
	}

}
