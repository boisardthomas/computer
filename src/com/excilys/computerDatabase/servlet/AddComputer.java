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

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.dao.ComputerDAO;

public class AddComputer extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CompanyDAO cdao = new CompanyDAO();
		ArrayList<Company> companyArray = cdao.getListCompany();
		
		req.setAttribute("companies", companyArray);		
		req.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(req, resp);
	}

	@SuppressWarnings({ "deprecation", "deprecation" })
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ComputerDAO cdao = new ComputerDAO(); 
		
		String name =req.getParameter("name");
		Date intro = null;
		Date disc = null; 
		String company = req.getParameter("company");
		
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
		
		
		int id_comp = 0;
		
		try
		{
			id_comp = Integer.parseInt(company);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		cdao.addComputer(name, intro, disc, id_comp);
		
		req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}
	
	

}
