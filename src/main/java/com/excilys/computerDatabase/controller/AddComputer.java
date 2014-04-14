package com.excilys.computerDatabase.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.validator.ComputerValidator;


@Controller
@RequestMapping("/addComputer")
public class AddComputer {
			
	@Autowired
	private CompanyService cs;
	@Autowired
	private ComputerService cpts;	
	
	@Autowired
	private ComputerValidator computerValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(computerValidator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView getAddComputer(ModelMap map)
	{
		ModelAndView mav = new ModelAndView("addComputer","computerDTO",new ComputerDTO());		
		
		ArrayList<Company> companyArray = cs.getListCompany();
		
		mav.getModelMap().addAttribute("companies", companyArray);		
		
		return mav;
	}
	
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource messageSource()
	{
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
	    bean.setBasename("messages");
	    return bean;
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView creatNewComputer(@ModelAttribute @Valid ComputerDTO cdto,
			BindingResult result,
			final RedirectAttributes redirectAttributes)
	{
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors())
		{
			ArrayList<Company> companyArray = cs.getListCompany();
			
			mav.getModelMap().addAttribute("companies", companyArray);
			mav.setViewName("addComputer");
			return mav;
		}
						
		String pattern = messageSource().getMessage("validator.date", null,LocaleContextHolder.getLocale());
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		
		String name = cdto.getName();
		String sIntro = cdto.getIntroduced();
		String sDisc = cdto.getDiscontinued();
		LocalDate intro = null;
		LocalDate disc = null; 
		String company = cdto.getCompany()+"";
		
		int id_comp = 0;
		
		try
		{
			id_comp = Integer.parseInt(company);
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
			
		cpts.addComputer(name, intro, disc, id_comp);
			
		int page = (int)(Math.ceil(cpts.nbComputer("")/15.0));
		
		mav.setViewName("redirect:/ListComputer?page="+page);
		
		String message = "New computer "+cdto.getName()+" was successfully created.";
		
		redirectAttributes.addFlashAttribute("message", message);
				
		return mav;
		
	}

}
