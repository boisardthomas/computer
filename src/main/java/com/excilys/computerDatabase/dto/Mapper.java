package com.excilys.computerDatabase.dto;

import org.joda.time.LocalDate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.service.CompanyService;

public class Mapper {

	public static Computer convertToComputer(ComputerDTO cdto)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("application-Context.xml");
		CompanyService cs = context.getBean(CompanyService.class);
	
		try {
			return Computer.builder().name(cdto.getNom())
					.introduced(LocalDate.parse(cdto.getIntroduced()))
					.discontinued(LocalDate.parse(cdto.getDiscontinued()))
					.company(cs.getCompany(cdto.getCompany()).name)
					.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Computer.builder().name(cdto.getNom())
					.introduced(new LocalDate(0))
					.discontinued(new LocalDate(0))
					.company(cdto.getCompany()+"")
					.build();
		}
		finally
		{
			((ClassPathXmlApplicationContext)context).close();
		}
	
	}
	
	
}
