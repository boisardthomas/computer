package com.excilys.computerDatabase.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.LocalDate;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.service.CompanyService;

public class Mapper {

	public static Computer convertToComputer(ComputerDTO cdto)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return Computer.builder().name(cdto.getNom())
					.introduced(new LocalDate(sdf.parse(cdto.getIntroduced()).getTime()))
					.discontinued(new LocalDate(sdf.parse(cdto.getDiscontinued()).getTime()))
					.company(CompanyService.getInstance().getCompany(cdto.getCompany()).name)
					.build();
		} catch (ParseException e) {
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
