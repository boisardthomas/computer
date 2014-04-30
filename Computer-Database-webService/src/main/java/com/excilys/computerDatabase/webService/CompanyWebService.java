package com.excilys.computerDatabase.webService;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.service.CompanyService;

@WebService
public class CompanyWebService {

	@Autowired
	private CompanyService companyService;
	
	public List<Company> findAll()
	{
		return companyService.getListCompany();
	}
}
