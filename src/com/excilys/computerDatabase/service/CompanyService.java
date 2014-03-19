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
	
	
	private CompanyService()
	{
		cdao = CompanyDAO.getInstance();
		cd = ComputerDatabase.getInstance();
		ldao = LogDAO.getInstance();
	}
	
	public static CompanyService getInstance()
	{
		if(cs == null)
			cs = new CompanyService();
		return cs;
	}
	
	public ArrayList<Company> getListCompany()
	{
		Connection cn = cd.getConnection();
		
		ArrayList<Company> companies= new ArrayList<>();
		
		try {
			ldao.addLog(cn, "list all company", "select");
			companies.addAll(cdao.getListCompany(cn));
			cn.commit();
			cn.close();
			cd.closeConnection();
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
		
		return companies;
	}	
	
}
