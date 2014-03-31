package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.dao.LogDAO;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

@Service
public class ComputerService {
	
	@Autowired
	private ComputerDAO computerDAO;
	
	@Autowired
	private ComputerDatabase cd;
	
	@Autowired
	private LogDAO ldao;
		
	public ComputerService()
	{
			
	}
	
	public ArrayList<Computer> getList(String search, String typeOrd, String ord, int page)
	{
		Connection cn = cd.getConnection();
		
		ArrayList<Computer> computers = new ArrayList<>();
		
		try {
			computers.addAll(computerDAO.getListComputer(search, typeOrd, ord, page));
			ldao.addLog("list all computer where name or company name like : "+ search, "select");
			cn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally
		{
			cd.closeConnection();
		}
		
		return computers;
	}
	
	public int nbComputer(String search)
	{
		Connection cn = cd.getConnection();
		
		int nbComputer =0;
		
		try {
			nbComputer = computerDAO.nbComputer(search);
			ldao.addLog("get number of computer where name or company name like : "+ search, "select");
			cn.commit();
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
		finally
		{
			cd.closeConnection();
		}
		
		return nbComputer;
	}
		
	public Computer getComputer(int id)
	{
		Connection cn = cd.getConnection();
		
		Computer computer = null;
		
		try {
			computer = computerDAO.getComputer(id);
			ldao.addLog("get computer where id="+id, "select");
			cn.commit();
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
		finally
		{
			cd.closeConnection();
		}
		
		return computer;
	}
	
	public void addComputer(String name, LocalDate intro, LocalDate disc, int company)
	{
		Connection cn = cd.getConnection();
				
		try {
			Long key =computerDAO.addComputer(name, intro, disc, company);
			ldao.addLog("add computer where id =" + key , "insert");
			cn.commit();
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
		finally
		{
			cd.closeConnection();
		}
		
	}
	
	public void updateComputer(int id, String name, LocalDate intro, LocalDate disc, int company_id)
	{
		Connection cn = cd.getConnection();
	
		try {
			ldao.addLog("update computer where id="+id, "update");
			computerDAO.updateComputer(id, name, intro, disc, company_id);
			cn.commit();
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
		finally
		{
			cd.closeConnection();
		}
	
	}
	
	public void deleteComputer(int id)
	{
		Connection cn = cd.getConnection();
				
		try {
			ldao.addLog("Delete computer  where id="+id, "delete");
			computerDAO.deleteComputer(id);
			cn.commit();
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
		finally
		{
			cd.closeConnection();
		}
		
	}
	
	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	public void setCd(ComputerDatabase cd) {
		this.cd = cd;
	}

	public void setLdao(LogDAO ldao) {
		this.ldao = ldao;
	}
	
}
