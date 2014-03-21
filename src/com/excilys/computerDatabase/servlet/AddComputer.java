package com.excilys.computerDatabase.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

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
		CompanyService cs = CompanyService.getInstance();
		ArrayList<Company> companyArray = cs.getListCompany();
		
		req.setAttribute("companies", companyArray);		
		req.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ComputerService cs = ComputerService.getInstance(); 
		
		String name =req.getParameter("name");
		Date intro = null;
		Date disc = null; 
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
		ComputerDTO cdto = new ComputerDTO(name,req.getParameter("introducedDate"),req.getParameter("discontinuedDate"),id_comp);
		
		boolean v1 = val.verification(cdto).get("name");
		boolean v2 = val.verification(cdto).get("introduction");
		boolean v3 = val.verification(cdto).get("discontinued");
		
		if(v1 == false || v2 == false || v3 == false)
		{
			System.out.println(v1);
			System.out.println(v2);
			System.out.println(v3);
			System.out.println(cdto);
			CompanyService comps = CompanyService.getInstance();
			ArrayList<Company> companyArray = comps.getListCompany();
			
			req.setAttribute("companies", companyArray);		
			
			req.setAttribute("verifName", v1);
			req.setAttribute("verifIntro", v2);
			req.setAttribute("verifDisc", v3);
			req.setAttribute("computerDTO", cdto);
			req.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(req, resp);
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				intro = sdf.parse(req.getParameter("introducedDate"));	
			} catch (ParseException e1) {
				intro = new Date(0);			
			}
			
			try
			{	
				disc  = sdf.parse(req.getParameter("discontinuedDate"));
			}
			catch (ParseException e1)
			{
				disc = new Date(0);
			}
			
			cs.addComputer(name, intro, disc, id_comp);
			
			int page = (int)(Math.ceil(cs.nbComputer("")/15.0));
			
			resp.sendRedirect("ListComputer?page="+page);			
		}
		
	}
	
	

}
