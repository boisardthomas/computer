package com.excilys.computerDatabase.dto;

import org.joda.time.LocalDate;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.service.CompanyService;

public class Mapper {

	public static Computer convertToComputer(ComputerDTO cdto)
	{
		try {
			return Computer.builder().name(cdto.getNom())
					.introduced(LocalDate.parse(cdto.getIntroduced()))
					.discontinued(LocalDate.parse(cdto.getDiscontinued()))
					.company(CompanyService.INSTANCE.getCompany(cdto.getCompany()).name)
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
	}
	
	
}
