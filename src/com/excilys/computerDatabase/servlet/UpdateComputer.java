package com.excilys.computerDatabase.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;

/**
 * Servlet implementation class UpdateComputer
 */
@WebServlet("/UpdateComputer")
public class UpdateComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
				
		CompanyService companyService = CompanyService.getInstance();
		ComputerService cs = ComputerService.getInstance();
		ArrayList<Company> companyArray = companyService.getListCompany();
		
		Computer computer = cs.getComputer(id);
		
		req.setAttribute("companies", companyArray);
		req.setAttribute("computer", computer);
		req.getRequestDispatcher("/WEB-INF/updateComputer.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ComputerService cs = ComputerService.getInstance(); 
		
		int id = Integer.parseInt(req.getParameter("id"));
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
		
		cs.updateComputer(id,name, intro, disc, id_comp);
		
		req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);		
	}

}
