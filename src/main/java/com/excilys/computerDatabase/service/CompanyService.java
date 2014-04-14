package com.excilys.computerDatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
			
	public CompanyService()
	{
		
	}
	
	@Transactional
	public ArrayList<Company> getListCompany()
	{
		ArrayList<Company> companies= new ArrayList<>();
		
		try {
			ldao.addLog("list all company", "select");
			companies.addAll(cdao.getListCompany());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return companies;
	}	
	
	@Transactional
	public Company getCompany(long l)
	{
		Company cpn = null;
		
		try {
			cpn = cdao.getCompany(l);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return cpn;
	}
	
	public void setCdao(CompanyDAO cdao) {
		this.cdao = cdao;
	}

	public void setLdao(LogDAO ldao) {
		this.ldao = ldao;
	}
		
}
