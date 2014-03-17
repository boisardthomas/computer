package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public class CompanyService {

	private static CompanyDAO cdao;
	private static CompanyService cs;
	private static ComputerDatabase cd;
	
	private CompanyService()
	{
		cdao = CompanyDAO.getInstance();
		cd = ComputerDatabase.getInstance();
	}
	
	public static CompanyService getInstance()
	{
		if(cs == null)
			cs = new CompanyService();
		return cs;
	}
	
	public ArrayList<Company> getListCompany()
	{
		return cdao.getListCompany(cd.getConnection());
	}	
	
}
