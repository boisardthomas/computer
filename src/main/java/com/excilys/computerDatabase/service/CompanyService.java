package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.dao.LogDAO;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public enum CompanyService {
	INSTANCE;
	
	private static CompanyDAO cdao = CompanyDAO.INSTANCE;
	private static LogDAO ldao = LogDAO.INSTANCE;
	private static ComputerDatabase cd = ComputerDatabase.INSTANCE;
		
	public ArrayList<Company> getListCompany()
	{
		Connection cn = cd.getConnection();
		
		ArrayList<Company> companies= new ArrayList<>();
		
		try {
			ldao.addLog("list all company", "select");
			companies.addAll(cdao.getListCompany(cn));
			cn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			cd.closeConnection();
		}
		
		return companies;
	}	
	
	public Company getCompany(long l)
	{
		cd.getConnection();
		Company cpn = null;
		
		try {
			cpn = cdao.getCompany(l);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			cd.closeConnection();
		}
		
		return cpn;
	}
	
}
