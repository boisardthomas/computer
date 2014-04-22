package com.excilys.computerDatabase.dto;


import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.bean.Computer;

@Component
public class Mapper {

	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	public ComputerDTO convertToComputerDTO(Computer cdto)
	{
		
		//String pattern = messageSource().getMessage("validator.date", null,LocaleContextHolder.getLocale());
		String pattern = messageSource.getMessage("validator.date", null,LocaleContextHolder.getLocale());
		
		if(cdto!=null)
		{
			Long id = cdto.getId();
			String name = cdto.getName().toString();
			String intro = (cdto.getIntroducedDate()!=null)?cdto.getIntroducedDate().toString(pattern):null;
			String disc = (cdto.getDiscontinuedDate()!=null)?cdto.getDiscontinuedDate().toString(pattern):null;
			long id_comp = (cdto.getCompany()!=null)?cdto.getCompany().getId():0;
			String name_comp = (cdto.getCompany()!=null)?cdto.getCompany().getName():null;
			return new ComputerDTO(id,name,intro,disc,id_comp,name_comp);
		}
		else
			return null;
	}
	
	public Computer convertToComputer(ComputerDTO cdto)
	{
		if(cdto!=null)
		{
			String pattern = messageSource.getMessage("validator.date", null,LocaleContextHolder.getLocale());
			
			DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
			
			Long id = cdto.getId();
			String name = cdto.getName();
			String sIntro = cdto.getIntroduced();
			String sDisc = cdto.getDiscontinued();
			LocalDate intro = null;
			LocalDate disc = null; 
			String companyId = cdto.getCompany()+"";
			String companyName = cdto.getCompanyName();
			
			Long id_comp = null;
			
			try
			{
				id_comp = Long.parseLong(companyId);
				id_comp = (id_comp == 0)?null:id_comp;
			}
			catch(NumberFormatException e)
			{
				e.printStackTrace();
			}
			
			if(sIntro==null || sIntro.equals(""))
				intro =null;
			else
				intro = dtf.parseLocalDate(cdto.getIntroduced());
			
			if(sDisc==null || sDisc.equals(""))
				disc = null;
			else
				disc  = dtf.parseLocalDate(cdto.getDiscontinued()); 
			
			Company company = null;
			
			if(id_comp!=null)
				company = Company.builder().id(id_comp).name(companyName).build();
			
			Computer c = Computer.builder().id(id).name(name).introduced(intro).discontinued(disc).company(company).build();
			System.out.println(c);
			return c;
		}
		else
			return null;
	}
	
}
