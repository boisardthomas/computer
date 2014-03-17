package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public class ComputerDAO {

	Logger log = LoggerFactory.getLogger(ComputerDAO.class);
	
	public ArrayList<Computer> getListComputer(String search, String typeOrd, String ord, int page)
	{
		ArrayList<Computer> computerArray = new ArrayList<>();
		
		log.info("Start search for computer");
		
		PreparedStatement st=null;
		ResultSet rs=null;
		Connection cn=null;
		try {
			cn = ComputerDatabase.getInstance();
						
			StringBuilder sb = new StringBuilder();
			sb.append("select cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cpny.name from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.name like '%");
			sb.append(search);
			sb.append("%' or cpny.name like '%");
			sb.append(search);
			sb.append("%'");
			
			if(typeOrd!=null && !typeOrd.equals("") && ord!=null && !ord.equals(""))
			{
				sb.append(" order by ");
				
				switch(typeOrd)
				{
					case "comp_name":
						sb.append("cpt.name");
						break;
					case "comp_intro":
						sb.append("cpt.introduced");
						break;
					case "comp_disc":
						sb.append("cpt.discontinued");
						break;
					case "cpny_name":
						sb.append("cpny.name");
						break;
				}
				sb.append(",cpt.name ");
				sb.append(ord);
			}
			
			sb.append(" limit ");
			sb.append((page-1)*15);
			sb.append(",15");
			
			st = cn.prepareStatement(sb.toString());
						
			rs = st.executeQuery();
			
			while(rs.next())
			{
				//new Computer (rs.getLong(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5));
				Computer c = Computer.builder().id(rs.getLong(1))
									.name(rs.getString(2))
									.introduced(rs.getDate(3))
									.discontinued(rs.getDate(4))
									.company(rs.getString(5))
									.build();
				computerArray.add(c);
			}
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		finally
		{
			try {
				st.close();
				rs.close();
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		log.info("End of search for computer");
		return computerArray;
	}
	
	public int nbComputer(String search)
	{
		int nbComputer = 0;
		
		Connection cn=null;
		PreparedStatement st=null;
		ResultSet rs = null;
		
		try 
		{
			cn = ComputerDatabase.getInstance();
			
			StringBuilder sb = new StringBuilder();
			sb.append("select count(*) as nbComputer from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.name like '%");
			sb.append(search);
			sb.append("%' or cpny.name like '%");
			sb.append(search);
			sb.append("%'");
			
			st = cn.prepareStatement(sb.toString());
			
			rs = st.executeQuery();
			
			rs.next();
			
			nbComputer = rs.getInt(1);
		}
		catch(SQLException e)
		{
			try {
				st.close();
				rs.close();
				cn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
		finally
		{
			try {
				st.close();
				rs.close();
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		log.info("Return number of computer");
		return nbComputer; 
	}
		
	public Computer getComputer(int id)
	{
		Computer computer=null;
		
		Connection cn=null;
		PreparedStatement st=null;
		ResultSet rs = null;
		
		try {
			cn = ComputerDatabase.getInstance();
						
			String req = "select cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cpny.name from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.id=?;";
			
			st = cn.prepareStatement(req);
			st.setInt(1, id);			
			rs = st.executeQuery();
			
			while(rs.next())
			{
				computer= new Computer (rs.getLong(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5));
			}
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally
		{
			try {
				st.close();
				rs.close();
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		log.info("Getting the computer");
		return computer;
	}
	
	public void addComputer(String name, Date intro, Date disc, int company)
	{
		log.info("Start inserting a new computer");
		
		Connection cn = null;
		PreparedStatement st = null;
		
		try {
			cn = ComputerDatabase.getInstance();
						
			String req = "insert into computer values(default,?,?,?,?)";
			
			st = cn.prepareStatement(req);
			
			st.setString(1, name);
			st.setDate(2, new java.sql.Date(intro.getTime()));
			st.setDate(3, new java.sql.Date(disc.getTime()));
			st.setInt(4, company);
			
			st.executeUpdate();
			
			st.close();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		log.info("Computer has been inserted successfully");
	}
	
	public void updateComputer(int id, String name, Date intro, Date disc, int company_id)
	{
		log.info("Start updating computer");

		Connection cn = null;
		PreparedStatement st = null;	
		
		try {
			cn = ComputerDatabase.getInstance();
						
			String req = "update computer set name=?, introduced=?, discontinued=?, company_id=? where id =?;";
			
			st = cn.prepareStatement(req);
			
			st.setString(1, name);			
			st.setDate(2, new java.sql.Date(intro.getTime()));
			st.setDate(3, new java.sql.Date(disc.getTime()));
			
			if(company_id>0)
			{
				st.setInt(4, company_id);
			}
			else
			{
				st.setNull(4,Types.INTEGER);
			}
			st.setInt(5, id);
			
			st.executeUpdate();
			
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		log.info("Computer has been updated successfully");
	}
	
	public void deleteComputer(int id)
	{
		log.info("Start deleting computer");
		
		Connection cn = null;
		PreparedStatement st = null;
		
		try {
			cn = ComputerDatabase.getInstance();
						
			String req = "delete from computer where id=?;";
			
			st = cn.prepareStatement(req);
			
			st.setInt(1, id);			
			
			st.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		log.info("Computer has been deleted successfully");
	}

}
