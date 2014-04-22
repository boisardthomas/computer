package com.excilys.computerDatabase.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.bean.Company;

@Repository
public class CompanyDAO {
	
	private static Logger log = LoggerFactory.getLogger(CompanyDAO.class);
			
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Company> getListCompany() throws SQLException
	{
		log.info("start search for company");
		
		@SuppressWarnings("unchecked")
		List<Company> companyArray = sessionFactory.getCurrentSession().createCriteria(Company.class).list();
						
		log.info("end of search for company");
		
		return companyArray;
	}
	
}
