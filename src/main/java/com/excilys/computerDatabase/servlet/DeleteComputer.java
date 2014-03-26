package com.excilys.computerDatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.service.ComputerService;

/**
 * Servlet implementation class DeleteComputer
 */
@WebServlet("/DeleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try
		{
			int id = Integer.parseInt(req.getParameter("id"));
			
			ComputerService cs = ComputerService.getInstance();
			
			cs.deleteComputer(id); 
			
			req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
		}
		catch(Exception e)
		{
			resp.sendRedirect("/WEB-INF/index.jsp");
		}
	}

}
