package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.dao.LogDAO;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyDAO cdao;
	
	@Autowired
	private LogDAO ldao;
	
	@Autowired
	private ComputerDatabase cd;
	
	public CompanyService()
	{
		
	}
	
	public ArrayList<Company> getListCompany()
	{
		Connection cn = cd.getConnection();
		
		ArrayList<Company> companies= new ArrayList<>();
		System.out.println(cdao);
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
	
	public void setCdao(CompanyDAO cdao) {
		this.cdao = cdao;
	}

	public void setLdao(LogDAO ldao) {
		this.ldao = ldao;
	}

	public void setCd(ComputerDatabase cd) {
		this.cd = cd;
	}
		
}
