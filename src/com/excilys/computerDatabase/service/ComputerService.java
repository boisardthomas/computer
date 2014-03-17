package com.excilys.computerDatabase.service;

import java.util.ArrayList;
import java.util.Date;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.dao.ComputerDAO;

public class ComputerService {

	private static ComputerDAO computerDAO;
	
	private ComputerService computerService;
	
	private ComputerService()
	{
		computerDAO = new ComputerDAO();
		computerService = new ComputerService();
	}
	
	public ComputerService getInstance()
	{
		if(computerService == null)
			computerService  = new ComputerService();
		return computerService;
	}
	
	public ArrayList<Computer> getList(String search, String typeOrd, String ord, int page)
	{
		return computerDAO.getListComputer(search, typeOrd, ord, page);
	}
	
	public int nbComputer(String search)
	{
		return computerDAO.nbComputer(search);
	}
		
	public Computer getComputer(int id)
	{
		return computerDAO.getComputer(id);
	}
	
	public void addComputer(String name, Date intro, Date disc, int company)
	{
		computerDAO.addComputer(name, intro, disc, company);
	}
	
	public void updateComputer(int id, String name, Date intro, Date disc, int company_id)
	{
		computerDAO.updateComputer(id, name, intro, disc, company_id);
	}
	
	public void deleteComputer(int id)
	{
		computerDAO.deleteComputer(id);
	}
	
}
