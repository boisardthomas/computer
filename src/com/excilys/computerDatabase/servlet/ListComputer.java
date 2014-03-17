package com.excilys.computerDatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.dao.ComputerDAO;

public class ListComputer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String search = req.getParameter("search");
		String typeOrd = req.getParameter("typeOrd");
		String ord = req.getParameter("ord");
		int page;
		
		try
		{
			page= Integer.parseInt(req.getParameter("page"));		
		}
		catch(NumberFormatException nfe)
		{
			page=1;
		}
		
		ComputerDAO cdao= new ComputerDAO();
		
		ArrayList<Computer> computerArray;
		int nbComputer;
		
		if(search!=null)
		{
			computerArray = cdao.getListComputer(search,typeOrd,ord,page);
			nbComputer = cdao.nbComputer(search);
		}
		else
		{
			computerArray = cdao.getListComputer("",typeOrd,ord,page);
			nbComputer = cdao.nbComputer("");
		}
		
		req.setAttribute("computerList", computerArray);
		req.setAttribute("nbOfComputer", nbComputer);
		
		req.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
	}


	
}
