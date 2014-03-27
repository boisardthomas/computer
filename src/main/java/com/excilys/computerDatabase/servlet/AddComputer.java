package com.excilys.computerDatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.validator.Validator;

public class AddComputer extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CompanyService cs = CompanyService.INSTANCE;
		ArrayList<Company> companyArray = cs.getListCompany();
		
		req.setAttribute("companies", companyArray);		
		req.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ComputerService cs = ComputerService.INSTANCE; 
		
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
			CompanyService comps = CompanyService.INSTANCE;
			ArrayList<Company> companyArray = comps.getListCompany();
			
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
			
			cs.addComputer(name, intro, disc, id_comp);
			
			int page = (int)(Math.ceil(cs.nbComputer("")/15.0));
			
			resp.sendRedirect("ListComputer?page="+page);			
		}
		
	}
	
	

}
