package com.excilys.computerDatabase.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.bean.Computer;

@Repository
public class ComputerDAO {
	
	private Logger log = LoggerFactory.getLogger(ComputerDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Computer> getListComputer(String search,
			String typeOrd, String ord, int page) throws SQLException {
		List<Computer> computerArray = new ArrayList<>();

		log.info("Start search for computer");

		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Computer.class);
		cr.createAlias("company", "company", JoinType.LEFT_OUTER_JOIN);
		cr.add(Restrictions.or(Restrictions.like("name", "%"+search+"%"),
				Restrictions.like("company.name", "%"+search+"%")));
		
		if (typeOrd != null && !typeOrd.equals("") && ord != null	&& !ord.equals("")) {
			ord = (ord.equals("asc") || ord.equals("desc"))?ord:"asc";
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
			if(ord.equals("asc"))
				cr.addOrder(Order.asc(selectColOrd));
			else
				cr.addOrder(Order.desc(selectColOrd));
			cr.addOrder(Order.asc("name"));
		}
		
		cr.setFirstResult((page - 1) * 15);
		cr.setMaxResults(15);
		
		computerArray = cr.list();
		
		log.info("End of search for computer");
		return computerArray;
	}

	public Long nbComputer(String search) throws SQLException {
		Long nbComputer = 0L;

		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Computer.class);
		cr.createAlias("company", "company", JoinType.LEFT_OUTER_JOIN);
		cr.add(Restrictions.or(Restrictions.like("name", "%"+search+"%"),
				Restrictions.like("company.name", "%"+search+"%")));
		cr.setProjection(Projections.rowCount());
		
		nbComputer = (Long) cr.list().get(0);
		
		log.info("Return number of computer");
		return nbComputer;
	}

	public Computer getComputer(Long id) throws SQLException {
		Computer computer = (Computer) sessionFactory.getCurrentSession().get(Computer.class,id);
			
		log.info("Getting the computer");
		return computer;
	}

	public Long addComputer(final Computer computer) throws SQLException {
		log.info("Start inserting a new computer");
		
		sessionFactory.getCurrentSession().save(computer);
		
		Long key=computer.getId();
		log.info("Computer" + key +" has been inserted successfully");
		
		return key;
				
	}

	public void updateComputer(Computer computer) throws SQLException {
		log.info("Start updating computer");
				
		sessionFactory.getCurrentSession().update(computer);

		log.info("Computer has been updated successfully");
	}

	public void deleteComputer(int id) throws SQLException {
		log.info("Start deleting computer");

		sessionFactory.getCurrentSession().createQuery("delete from Computer c where c.id='"+id+"'").executeUpdate();
		
		log.info("Computer has been deleted successfully");
	}
	
}
