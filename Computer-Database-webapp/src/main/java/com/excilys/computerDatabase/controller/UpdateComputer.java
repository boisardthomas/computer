package com.excilys.computerDatabase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.dto.Mapper;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.validator.ComputerValidator;


@Controller
@RequestMapping("/updateComputer")
public class UpdateComputer{
	
	@Autowired
	private CompanyService cs;
	
	@Autowired
	private ComputerService cpts;	
	
	@Autowired
	private ComputerValidator computerValidator;
	
	@Autowired
	private Mapper Mapper;
		
	@InitBinder
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(computerValidator);
	}

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource messageSource()
	{
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
	    bean.setBasename("messages");
	    return bean;
	}	
	
	@RequestMapping(method = RequestMethod.GET) 
	protected ModelAndView getUpdateComputer(ModelMap map, @RequestParam("id") long id)
			throws ServletException, IOException {
		ArrayList<Company> companyArray = cs.getListCompany();
		
		ComputerDTO computerDTO = Mapper.convertToComputerDTO(cpts.getComputer(id));
		
		ModelAndView mav = new ModelAndView("updateComputer","computerDTO",computerDTO);	
		
		mav.getModelMap().addAttribute("companies", companyArray);
		
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST) 
	protected ModelAndView uptdateComputer(@ModelAttribute @Valid ComputerDTO cdto,
			BindingResult result,
			final RedirectAttributes redirectAttributes)
	{
		
		ModelAndView mav = new ModelAndView();	
				
		if(result.hasErrors())
		{
			ArrayList<Company> companyArray = cs.getListCompany();
			
			mav.getModelMap().addAttribute("companies", companyArray);
			mav.setViewName("updateComputer");
			return mav;
		}
		
		Computer c = Mapper.convertToComputer(cdto);
		
		cpts.updateComputer(c);
		
		mav.setViewName("redirect:/ListComputer?page=1");
		
		return mav;
		
	}
	
}
