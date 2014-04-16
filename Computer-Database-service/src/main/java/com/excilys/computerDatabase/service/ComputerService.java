package com.excilys.computerDatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.dao.LogDAO;

@Service
public class ComputerService {
	
	@Autowired
	private ComputerDAO computerDAO;
		
	@Autowired
	private LogDAO ldao;
		
	public ComputerService()
	{
			
	}
	
	@Transactional
	public ArrayList<Computer> getList(String search, String typeOrd, String ord, int page)
	{
		ArrayList<Computer> computers = new ArrayList<>();
		
		try {
			computers.addAll(computerDAO.getListComputer(search, typeOrd, ord, page));
			ldao.addLog("list all computer where name or company name like : "+ search, "select");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return computers;
	}
	
	@Transactional
	public int nbComputer(String search)
	{
		int nbComputer =0;
		
		try {
			nbComputer = computerDAO.nbComputer(search);
			ldao.addLog("get number of computer where name or company name like : "+ search, "select");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return nbComputer;
	}
	
	@Transactional
	public Computer getComputer(int id)
	{
		Computer computer = null;
		
		try {
			computer = computerDAO.getComputer(id);
			ldao.addLog("get computer where id="+id, "select");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return computer;
	}
	
	@Transactional
	public void addComputer(Computer computer)
	{
		
				
		try {
			Long key =computerDAO.addComputer(computer);
			ldao.addLog("add computer where id =" + key , "insert");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	@Transactional
	public void updateComputer(Computer c)
	{
		
		try {
			ldao.addLog("update computer where id="+c.getId(), "update");
			computerDAO.updateComputer(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	@Transactional
	public void deleteComputer(int id)
	{
			
		try {
			ldao.addLog("Delete computer  where id="+id, "delete");
			computerDAO.deleteComputer(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	public void setLdao(LogDAO ldao) {
		this.ldao = ldao;
	}
	
}
