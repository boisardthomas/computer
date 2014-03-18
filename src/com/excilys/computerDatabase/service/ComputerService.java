package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.dao.LogDAO;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public class ComputerService {

	private static ComputerDAO computerDAO;
	private static ComputerDatabase cd;
	private static ComputerService computerService;
	private static LogDAO ldao;
	
	private ComputerService()
	{
		computerDAO = ComputerDAO.getInstance();
		cd = ComputerDatabase.getInstance();
		ldao = LogDAO.getInstance();
	}
	
	public static ComputerService getInstance()
	{
		if(computerService == null)
			computerService  = new ComputerService();
		return computerService;
	}
	
	public ArrayList<Computer> getList(String search, String typeOrd, String ord, int page)
	{
		Connection cn = cd.getConnection();
		ldao.addLog(cn, "list all computer where name or company name like : "+ search, "select");
		return computerDAO.getListComputer(cn,search, typeOrd, ord, page);
	}
	
	public int nbComputer(String search)
	{
		Connection cn = cd.getConnection();
		ldao.addLog(cn, "get number of computer where name or company name like : "+ search, "select");
		return computerDAO.nbComputer(cn,search);
	}
		
	public Computer getComputer(int id)
	{
		Connection cn = cd.getConnection();
		ldao.addLog(cn, "get computer where id="+id, "select");
		return computerDAO.getComputer(cn,id);
	}
	
	public void addComputer(String name, Date intro, Date disc, int company)
	{
		Connection cn = cd.getConnection();
		ldao.addLog(cn, "add computer", "insert");
		computerDAO.addComputer(cn,name, intro, disc, company);
	}
	
	public void updateComputer(int id, String name, Date intro, Date disc, int company_id)
	{
		Connection cn = cd.getConnection();
		ldao.addLog(cn, "update computer where id="+id, "update");
		computerDAO.updateComputer(cn,id, name, intro, disc, company_id);
	}
	
	public void deleteComputer(int id)
	{
		Connection cn = cd.getConnection();
		ldao.addLog(cn, "Delete computer  where id="+id, "delete");
		computerDAO.deleteComputer(cn,id);
	}
	
}
