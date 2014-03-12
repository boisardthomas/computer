package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public class ComputerDAO {

	public ArrayList<Computer> getListComputer()
	{
		ArrayList<Computer> computerArray = new ArrayList<>();
		
		try {
			Connection cn = ComputerDatabase.getInstance();
						
			String req = "select cpt.name, cpt.introduced, cpt.discontinued, cpny.name from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id;";
			
			PreparedStatement st = cn.prepareStatement(req);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				Computer c = new Computer (rs.getString(1),rs.getDate(2),rs.getDate(3),rs.getString(4));
				computerArray.add(c);
			}
			
			st.close();
			rs.close();
			cn=null;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return computerArray;
	}
	
	public void addComputer(String name, Date intro, Date disc, int company)
	{
		try {
			Connection cn = ComputerDatabase.getInstance();
						
			String req = "insert into computer values(default,?,?,?,?)";
			
			PreparedStatement st = cn.prepareStatement(req);
			
			st.setString(1, name);
			st.setDate(2, new java.sql.Date(intro.getTime()));
			st.setDate(3, new java.sql.Date(disc.getTime()));
			st.setInt(4, company);
			
			st.executeUpdate();
			
			st.close();
			cn=null;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	

}
