package com.excilys.computerDatabase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.validator.Validator;


@Controller
@RequestMapping("/addComputer")
public class AddComputer {
			
	@Autowired
	private CompanyService cs;
	@Autowired
	private ComputerService cpts;	
	
	@RequestMapping(method = RequestMethod.GET)
	protected String getAddComputer(ModelMap map)
			throws ServletException, IOException {
		
		ArrayList<Company> companyArray = cs.getListCompany();
		
		map.addAttribute("companies", companyArray);		
		
		return "addComputer";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		String name = req.getParameter("name");
		String sIntro = req.getParameter("introducedDate");
		String sDisc = req.getParameter("discontinuedDate");
		LocalDate intro = null;
		LocalDate disc = null; 
		String company = req.getParameter("company");
		
		int id_comp = 0;
		
		try
		{
			id_comp = Integer.parseInt(company);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		Validator val = new Validator();
		ComputerDTO cdto = new ComputerDTO(name,sIntro,sDisc,id_comp);
		
		Map<String, Boolean> valid = val.verification(cdto);
		
		boolean v1 = valid.get("name");
		boolean v2 = valid.get("introduction");
		boolean v3 = valid.get("discontinued");
		boolean v4 = valid.get("discSupToIntro");
		
		if(v1 == false || v2 == false || v3 == false || v4 == false)
		{
			
			ArrayList<Company> companyArray = cs.getListCompany();
			
			req.setAttribute("companies", companyArray);		
			
			req.setAttribute("verifName", v1);
			req.setAttribute("verifIntro", v2);
			req.setAttribute("verifDisc", v3);
			req.setAttribute("verifDate", v4);
			req.setAttribute("computerDTO", cdto);
			
			req.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(req, resp);
		}
		else
		{
			
			if(sIntro==null || sIntro.equals(""))
				intro = new LocalDate(0);
			else
				intro = LocalDate.parse(sIntro);
			
			if(sDisc==null || sDisc.equals(""))
				disc = new LocalDate(0);
			else
				disc  = LocalDate.parse(sDisc);
			
			cpts.addComputer(name, intro, disc, id_comp);
			
			int page = (int)(Math.ceil(cpts.nbComputer("")/15.0));
		
			resp.sendRedirect("ListComputer?page="+page);			
		}
		
	}

}
