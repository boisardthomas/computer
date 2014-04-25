package com.excilys.computerDatabase.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.excilys.computerDatabase.bean.Computer;

public interface ComputerDAO extends JpaRepository<Computer, Long>{
	
	final String FIND_BY_NAME = "select computer from Computer computer left join computer.company company where computer.name like %:searchName% or company.name like %:searchName%";
	
	@Query(FIND_BY_NAME)
    public List<Computer> findByComputerOrCompanyName(@Param("searchName") String searchName);	
	
	@Query(FIND_BY_NAME)
    public Page<Computer> findByComputerOrCompanyName(@Param("searchName") String searchName, Pageable page);	
}
