package com.excilys.computerDatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.service.ComputerService;


public class ListComputer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerService cs;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String search = req.getParameter("search");
		String typeOrd = req.getParameter("typeOrd");
		String ord = req.getParameter("ord");
		int page=1;
		
		try
		{
			page= Integer.parseInt(req.getParameter("page"));
			
			
			
			ArrayList<Computer> computerArray;
			int nbComputer;
			
			if(search!=null)
			{
				computerArray = cs.getList(search,typeOrd,ord,page);
				nbComputer = cs.nbComputer(search);
			}
			else
			{
				computerArray = cs.getList("",typeOrd,ord,page);
				nbComputer = cs.nbComputer("");
			}
			
			req.setAttribute("computerList", computerArray);
			req.setAttribute("nbOfComputer", nbComputer);
			
			//((ClassPathXmlApplicationContext)context).close();
			
			req.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
		}
		catch(NumberFormatException nfe)
		{
			resp.sendError(404,"Vous venez d'être attrapé par la brigade des coincoins !!!");
		}
		
	}
	
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
	      getServletContext());
	}
	
		
}
