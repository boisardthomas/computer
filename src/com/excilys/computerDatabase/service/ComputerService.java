package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
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
		
		ArrayList<Computer> computers = new ArrayList<>();
		
		try {
			computers.addAll(computerDAO.getListComputer(search, typeOrd, ord, page));
			ldao.addLog(cn, "list all computer where name or company name like : "+ search, "select");
			cn.commit();
			cn.close();
			cd.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return computers;
	}
	
	public int nbComputer(String search)
	{
		Connection cn = cd.getConnection();
		
		int nbComputer =0;
		
		try {
			nbComputer = computerDAO.nbComputer(search);
			ldao.addLog(cn, "get number of computer where name or company name like : "+ search, "select");
			cn.commit();
			cn.close();
			cd.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return nbComputer;
	}
		
	public Computer getComputer(int id)
	{
		Connection cn = cd.getConnection();
		
		Computer computer = null;
		
		try {
			computer = computerDAO.getComputer(id);
			ldao.addLog(cn, "get computer where id="+id, "select");
			cn.commit();
			cn.close();
			cd.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return computer;
	}
	
	public void addComputer(String name, Date intro, Date disc, int company)
	{
		Connection cn = cd.getConnection();
				
		try {
			Long key =computerDAO.addComputer(name, intro, disc, company);
			ldao.addLog(cn, "add computer where id =" + key , "insert");
			cn.commit();
			cn.close();
			cd.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
		
	}
	
	public void updateComputer(int id, String name, Date intro, Date disc, int company_id)
	{
		Connection cn = cd.getConnection();
	
		try {
			ldao.addLog(cn, "update computer where id="+id, "update");
			computerDAO.updateComputer(id, name, intro, disc, company_id);
			cn.commit();
			cn.close();
			cd.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	}
	
	public void deleteComputer(int id)
	{
		Connection cn = cd.getConnection();
				
		try {
			ldao.addLog(cn, "Delete computer  where id="+id, "delete");
			computerDAO.deleteComputer(id);
			cn.commit();
			cn.close();
			cd.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
}
