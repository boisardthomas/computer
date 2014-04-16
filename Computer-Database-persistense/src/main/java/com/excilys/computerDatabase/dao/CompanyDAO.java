package com.excilys.computerDatabase.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.bean.Company;

@Repository
public class CompanyDAO {
	
	private static Logger log = LoggerFactory.getLogger(CompanyDAO.class);
			
	@Autowired
	private DataSource ds;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Company> getListCompany() throws SQLException
	{
		log.info("start search for company");
		
		List<Company> companyArray = new ArrayList<>();
				
		String req = "select * from company;";
		companyArray = jdbcTemplate.query(req, new BeanPropertyRowMapper<Company>(Company.class));
						
		log.info("end of search for company");
		
		return companyArray;
	}
	
}
