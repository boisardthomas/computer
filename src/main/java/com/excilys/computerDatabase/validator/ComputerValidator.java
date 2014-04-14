package com.excilys.computerDatabase.validator;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.computerDatabase.dto.ComputerDTO;

@Component
public class ComputerValidator implements Validator
{
	
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource messageSource()
	{
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
	    bean.setBasename("messages");
	    return bean;
	}	
	
	public boolean nameValidation(String name)
	{
		if(name.equals("") || name==null)
			return false;
		return true;
	}
	
	public boolean dateValidation(String date)
	{
		if(date==null || date.equals(""))
			return true;
		
		String pattern = messageSource().getMessage("validator.date", null,LocaleContextHolder.getLocale());
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		
		try
		{
			dtf.parseLocalDate(date).toString("yyyy-MM-dd"); 
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean bissextile(int annee)
	{
		if(annee%4 == 0 && annee%100 != 0)
			return true;
		if(annee%400 == 0)
			return true;
		return false;
	}
	
	public boolean discGreaterThanIntro(String pIntro, String pDisc)
	{
		if(pIntro==null || pIntro.equals("") || pDisc==null || pDisc.equals(""))
			return true;
			
		if(!dateValidation(pIntro) || !dateValidation(pDisc))
			return true;
		
		String pattern = messageSource().getMessage("validator.date", null,LocaleContextHolder.getLocale());
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		String intro = dtf.parseLocalDate(pIntro).toString("yyyy-MM-dd");
		String disc = dtf.parseLocalDate(pDisc).toString("yyyy-MM-dd");
		
		String[] tabDateIntro = intro.split("-");
		String[] tabDateDisc = disc.split("-");
		
		try
		{
			int anneeIntro = Integer.parseInt(tabDateIntro[0]);
			int moisIntro = Integer.parseInt(tabDateIntro[1]);
			int joursIntro = Integer.parseInt(tabDateIntro[2]);
			
			int anneeDisc = Integer.parseInt(tabDateDisc[0]);
			int moisDisc = Integer.parseInt(tabDateDisc[1]);
			int joursDisc = Integer.parseInt(tabDateDisc[2]);	
			
			if(anneeIntro>anneeDisc)
				return false;
			else if(anneeDisc == anneeIntro)
			{
				if(moisIntro>moisDisc)
					return false;
				else if(moisDisc == moisIntro)
				{
					if(joursIntro>=joursDisc)
						return false;
				}
			}
			return true;

		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ComputerDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ComputerDTO cdto = (ComputerDTO) target;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "validator.name","Computer's name shouldn't be empty."); 
		
		
		if(!dateValidation(cdto.getIntroduced()))
		{
			errors.rejectValue("introduced", "validator.intro","Introduced date format invalid.");
		}
		
		if(!dateValidation(cdto.getDiscontinued()))
		{
			errors.rejectValue("discontinued", "validator.disc","Discontinued date format is invalid.");
		}
		
		if(!discGreaterThanIntro(cdto.getIntroduced(),cdto.getDiscontinued()))
		{
			errors.rejectValue("discontinued", "validator.discSupIntro","Discontinued should be greatter than introduced date.");
		}
				
	}
	
}
