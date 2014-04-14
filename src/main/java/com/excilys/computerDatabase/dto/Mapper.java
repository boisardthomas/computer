package com.excilys.computerDatabase.dto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.bean.Computer;

@Component
public class Mapper {

	/*@Bean(name = "messageSource")
	public ResourceBundleMessageSource messageSource()
	{
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
	    bean.setBasename("messages");
	    return bean;
	}	*/

	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	public ComputerDTO convertToComputerDTO(Computer cdto)
	{
		
		//String pattern = messageSource().getMessage("validator.date", null,LocaleContextHolder.getLocale());
		String pattern = messageSource.getMessage("validator.date", null,LocaleContextHolder.getLocale());
		
		
		Long id = cdto.getId();
		String name = cdto.getName().toString();
		String intro = (cdto.getIntroducedDate()!=null)?cdto.getIntroducedDate().toString(pattern):null;
		String disc = (cdto.getDiscontinuedDate()!=null)?cdto.getDiscontinuedDate().toString(pattern):null;
		Long id_comp = (cdto.getId_Company()!=null)?cdto.getId_Company():null;
		return new ComputerDTO(id,name,intro,disc,id_comp);
	
	}
	
}
