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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String search = req.getParameter("search");
		String typeOrd = req.getParameter("typeOrd");
		String ord = req.getParameter("ord");
		
		ComputerDAO cdao= new ComputerDAO();
		ArrayList<Computer> computerArray = cdao.getListComputer(search,typeOrd,ord);
		
		req.setAttribute("computerList", computerArray);
				
		req.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
	}


	
}
