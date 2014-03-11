package com.excilys.computerDatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.dao.ComputerDAO;

public class AddComputer extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("addComputer.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ComputerDAO cdao = new ComputerDAO(); 
		
		String name =req.getParameter("name");
		String intro = req.getParameter("introducedDate");
		String disc = req.getParameter("discontinuedDate");
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
		
		cdao.addComputer(name, intro, disc, id_comp);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	

}
