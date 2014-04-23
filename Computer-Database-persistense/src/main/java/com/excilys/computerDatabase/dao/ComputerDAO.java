package com.excilys.computerDatabase.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.bean.QCompany;
import com.excilys.computerDatabase.bean.QComputer;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.types.OrderSpecifier;

@Repository
public class ComputerDAO {
	
	private Logger log = LoggerFactory.getLogger(ComputerDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Computer> getListComputer(String search,
			String typeOrd, String ord, int page) throws SQLException {
		List<Computer> computerArray = new ArrayList<>();

		log.info("Start search for computer");

		
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;		
		
		query.from(computer).leftJoin(computer.company,company);
		
		if (typeOrd != null && !typeOrd.equals("") && ord != null	&& !ord.equals("")) {
			ord = (ord.equals("asc") || ord.equals("desc"))?ord:"asc";
			
			switch (typeOrd) {
				case "comp_name":
					query = (ord.equals("asc"))?query.orderBy(computer.name.asc()):query.orderBy(computer.name.desc());
					break;
				case "comp_intro":
					query = (ord.equals("asc"))?query.orderBy(computer.introducedDate.asc(),computer.name.asc()):query.orderBy(computer.introducedDate.desc(),computer.name.asc());
					break;
				case "comp_disc":
					query = (ord.equals("asc"))?query.orderBy(computer.discontinuedDate.asc(),computer.name.asc()):query.orderBy(computer.discontinuedDate.desc(),computer.name.asc());
					break;
				case "cpny_name":
					query = (ord.equals("asc"))?query.orderBy(company.name.asc(),computer.name.asc()):query.orderBy(company.name.desc(),computer.name.asc());
					break;
				default:
					query.orderBy(computer.id.asc());
					break;
			}
			
		}
		query.offset((page-1)*15);
		query.limit(15);
		
		computerArray = query.list(computer);
		
		log.info("End of search for computer");
		return computerArray;
	}

	
	
	public Long nbComputer(String search) throws SQLException {
		Long nbComputer = 0L;

		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		
		QComputer computer = QComputer.computer;
		
		nbComputer = (Long) query.from(computer).leftJoin(computer.company).count();
				
		log.info("Return number of computer");
		return nbComputer;
	}

	public Computer getComputer(Long id) throws SQLException {
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QComputer computer = QComputer.computer;
		Computer c = (Computer) query.from(computer).where(computer.id.eq(id)).uniqueResult(computer);
			
		log.info("Getting the computer");
		return c;
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
