package com.excilys.computerDatabase.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.bean.QCompany;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class CompanyDAO {
	
	private static Logger log = LoggerFactory.getLogger(CompanyDAO.class);
			
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Company> getListCompany() throws SQLException
	{
		log.info("start search for company");
		
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		
		QCompany company = QCompany.company;
		
		List<Company> companyArray = query.from(company).list(company);		
		log.info("end of search for company");
		
		return companyArray;
	}
	
}
