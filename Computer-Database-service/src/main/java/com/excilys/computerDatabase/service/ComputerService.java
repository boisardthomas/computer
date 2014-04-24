package com.excilys.computerDatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
	public Page<Computer> getList(String search, String typeOrd, String ord, int page)
	{
		Page<Computer> computers=null;
		Direction dir = (ord.equals("asc"))?Direction.ASC:Direction.DESC;
		String selectColOrd;
		switch (typeOrd) {
			case "comp_name":
				selectColOrd = "name";
				break;
			case "comp_intro":
				selectColOrd = "introducedDate";
				break;
			case "comp_disc":
				selectColOrd = "discontinuedDate";
				break;
			case "cpny_name":
				selectColOrd = "company.name";
				break;
			default:
				selectColOrd = "id";
				break;
		}
		
		List<Order> listOrder = new ArrayList<Order>();
		listOrder.add(new Order(dir, selectColOrd));
		listOrder.add(new Order(dir, "name"));
		
		Pageable pageRequest = new PageRequest(page-1, 15,new Sort(listOrder));		
		try {
			computers = computerDAO.findByComputerOrCompanyName(search, pageRequest);//(search, typeOrd, ord, page));
			ldao.addLog("list all computer where name or company name like : "+ search, "select");
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return computers;
	}
	
	@Transactional
	public Long nbComputer(String search)
	{
		Long nbComputer =0L;
		
		try {
			//nbComputer = computerDAO.nbComputer(search);
			ldao.addLog("get number of computer where name or company name like : "+ search, "select");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return nbComputer;
	}
	
	@Transactional
	public Computer getComputer(Long id)
	{
		Computer computer = null;
		
		try {
			computer = computerDAO.findOne(id);
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
			computerDAO.save(computer);
			Long key =computer.getId();
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
			computerDAO.save(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	@Transactional
	public void deleteComputer(Long id)
	{
			
		try {
			ldao.addLog("Delete computer  where id="+id, "delete");
			computerDAO.delete(id);
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
