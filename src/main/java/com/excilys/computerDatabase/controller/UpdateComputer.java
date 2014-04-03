package com.excilys.computerDatabase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.dto.Mapper;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.validator.ComputerValidator;

/**
 * Servlet implementation class UpdateComputer
 */
@Controller
@RequestMapping("/updateComputer")
public class UpdateComputer{
	private static final long serialVersionUID = 1L;
	
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
	protected ModelAndView getUpdateComputer(ModelMap map, @RequestParam("id") int id)
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
		
		Long id = cdto.getId();
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
			intro = LocalDate.parse(sIntro);
		
		if(sDisc==null || sDisc.equals(""))
			disc = new LocalDate(0);
		else
			disc  = LocalDate.parse(sDisc);
		
		cpts.updateComputer(id,name, intro, disc, id_comp);
		
		mav.setViewName("redirect:/ListComputer?page=1");
		
		return mav;
		
	}
	
}
