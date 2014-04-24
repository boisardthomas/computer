package com.excilys.computerDatabase.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.computerDatabase.bean.Company;


public interface CompanyDAO extends JpaRepository<Company, Long>{
	
	
	
}
