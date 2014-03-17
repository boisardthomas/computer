package com.excilys.computerDatabase.service;

import java.util.ArrayList;
import java.util.Date;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public class ComputerService {

	private static ComputerDAO computerDAO;
	private static ComputerDatabase cd;
	private static ComputerService computerService;
	
	private ComputerService()
	{
		computerDAO = ComputerDAO.getInstance();
		cd = ComputerDatabase.getInstance();
	}
	
	public static ComputerService getInstance()
	{
		if(computerService == null)
			computerService  = new ComputerService();
		return computerService;
	}
	
	public ArrayList<Computer> getList(String search, String typeOrd, String ord, int page)
	{
		return computerDAO.getListComputer(cd.getConnection(),search, typeOrd, ord, page);
	}
	
	public int nbComputer(String search)
	{
		return computerDAO.nbComputer(cd.getConnection(),search);
	}
		
	public Computer getComputer(int id)
	{
		return computerDAO.getComputer(cd.getConnection(),id);
	}
	
	public void addComputer(String name, Date intro, Date disc, int company)
	{
		computerDAO.addComputer(cd.getConnection(),name, intro, disc, company);
	}
	
	public void updateComputer(int id, String name, Date intro, Date disc, int company_id)
	{
		computerDAO.updateComputer(cd.getConnection(),id, name, intro, disc, company_id);
	}
	
	public void deleteComputer(int id)
	{
		computerDAO.deleteComputer(cd.getConnection(),id);
	}
	
}
