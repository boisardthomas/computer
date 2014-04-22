package com.excilys.computerDatabase.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.bean.Computer;

@Repository
public class ComputerDAO {
	
	private Logger log = LoggerFactory.getLogger(ComputerDAO.class);
		
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Computer> getListComputer(String search,
			String typeOrd, String ord, int page) throws SQLException {
		List<Computer> computerArray = new ArrayList<>();

		log.info("Start search for computer");

		StringBuilder sb = new StringBuilder();
		sb.append("select computer from Computer computer left join  computer.company company where computer.name like :searchString");
		sb.append(" or company.name like :searchString");
		
		if (typeOrd != null && !typeOrd.equals("") && ord != null	&& !ord.equals("")) {
			sb.append(" order by ");
			ord = (ord.equals("asc") || ord.equals("desc"))?ord:"asc";
			switch (typeOrd) {
				case "comp_name":
					sb.append("computer.name");
					break;
				case "comp_intro":
					sb.append("computer.introducedDate");
					break;
				case "comp_disc":
					sb.append("computer.discontinuedDate");
					break;
				case "cpny_name":
					sb.append("company.name");
					break;
				default:
					sb.append("computer.id");
					break;
			}
			sb.append(",computer.name ");
			sb.append(ord);
		}

		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		query.setParameter("searchString","%" +search + "%");
		query.setFirstResult((page - 1) * 15);
		query.setMaxResults(15);
		computerArray = query.list();
	
		log.info("End of search for computer");
		return computerArray;
	}

	public Long nbComputer(String search) throws SQLException {
		Long nbComputer = 0L;

		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) from Computer computer left join  computer.company company where computer.name like :searchString");
		sb.append(" or company.name like :searchString");
		
		nbComputer = (Long)(sessionFactory.getCurrentSession().createQuery(sb.toString()).setParameter("searchString","%" +search + "%").uniqueResult());	
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
