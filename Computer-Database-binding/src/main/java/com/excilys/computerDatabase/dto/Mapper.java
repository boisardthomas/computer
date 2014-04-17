package com.excilys.computerDatabase.dto;


import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

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
			Long id_comp = (cdto.getId_Company()!=null)?cdto.getId_Company():null;
			String name_comp = cdto.getCompany();
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
			}
			catch(NumberFormatException e)
			{
				e.printStackTrace();
			}
			
			if(sIntro==null || sIntro.equals(""))
				intro = new LocalDate(0);
			else
				intro = dtf.parseLocalDate(cdto.getIntroduced());
			
			if(sDisc==null || sDisc.equals(""))
				disc = new LocalDate(0);
			else
				disc  = dtf.parseLocalDate(cdto.getDiscontinued()); 
			
			Computer c = Computer.builder().id(id).name(name).introduced(intro).discontinued(disc).id_company(id_comp).company(companyName).build();
			return c;
		}
		else
			return null;
	}
	
}
