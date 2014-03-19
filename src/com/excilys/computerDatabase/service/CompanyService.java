package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.dao.LogDAO;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public class CompanyService {

	private static CompanyDAO cdao;
	private static CompanyService cs;
	private static LogDAO ldao;
	private static ComputerDatabase cd;
	private ThreadLocal<Connection> threadConnection;
	
	private CompanyService()
	{
		cdao = CompanyDAO.getInstance();
		cd = ComputerDatabase.getInstance();
		ldao = LogDAO.getInstance();
		threadConnection = new ThreadLocal<Connection>();
	}
	
	public static CompanyService getInstance()
	{
		if(cs == null)
			cs = new CompanyService();
		return cs;
	}
	
	public ArrayList<Company> getListCompany()
	{
		//threadConnection.set(cd.getConnection());
		Connection cn = cd.getConnection();
		ldao.addLog(cn, "list all company", "select");
		ArrayList<Company> companies = cdao.getListCompany(cn);
		
		try {
			cn.commit();
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return companies;
	}	
	
}
