package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

@Repository
public class ComputerDAO {
	
	private Logger log = LoggerFactory.getLogger(ComputerDAO.class);
	
	@Autowired
	private ComputerDatabase computerDatabase;

	public ArrayList<Computer> getListComputer(String search,
			String typeOrd, String ord, int page) throws SQLException {
		ArrayList<Computer> computerArray = new ArrayList<>();

		log.info("Start search for computer");

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
				
		cn = computerDatabase.getConnection();

		StringBuilder sb = new StringBuilder();
		sb.append("select cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cpny.name from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.name like ? or cpny.name like ?");
		

		if (typeOrd != null && !typeOrd.equals("") && ord != null	&& !ord.equals("")) {
			sb.append(" order by ");
			ord = (ord.equals("asc") || ord.equals("desc"))?ord:"asc";
			switch (typeOrd) {
				case "comp_name":
					sb.append("cpt.name");
					break;
				case "comp_intro":
					sb.append("cpt.introduced");
					break;
				case "comp_disc":
					sb.append("cpt.discontinued");
					break;
				case "cpny_name":
					sb.append("cpny.name");
					break;
				default:
					sb.append("cpt.id");
					break;
			}
			sb.append(",cpt.name ");
			sb.append(ord);
		}

		sb.append(" limit ");
		sb.append((page - 1) * 15);
		sb.append(",15");
		
		st = cn.prepareStatement(sb.toString());

		st.setString(1, "%" + search + "%");
		st.setString(2, "%" + search + "%");
		
		rs = st.executeQuery();

		while (rs.next()) {
			LocalDate localIntro = null;
			LocalDate localDisc = null;
			java.sql.Date sqlIntro = rs.getDate(3);
			java.sql.Date sqlDisc = rs.getDate(4);
			
			if(sqlIntro!=null)
				localIntro = new LocalDate(sqlIntro.getTime());
			if(sqlDisc!=null)
				localDisc = new LocalDate(sqlDisc.getTime());
				
			Computer c = Computer.builder().id(rs.getLong(1))
					.name(rs.getString(2))
					.introduced(localIntro)
					.discontinued(localDisc)
					.company(rs.getString(5))
					.build();
			computerArray.add(c);
		}

		rs.close();
		st.close();

		log.info("End of search for computer");
		return computerArray;
	}

	public int nbComputer(String search) throws SQLException {
		int nbComputer = 0;

		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		cn = computerDatabase.getConnection();

		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) as nbComputer from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.name like ?");
		sb.append(" or cpny.name like ?");
		
		st = cn.prepareStatement(sb.toString());

		st.setString(1, "%" + search + "%");
		st.setString(2, "%" + search + "%");
		
		rs = st.executeQuery();

		rs.next();

		nbComputer = rs.getInt(1);

		st.close();
		rs.close();

		log.info("Return number of computer");
		return nbComputer;
	}

	public Computer getComputer(int id) throws SQLException {
		Computer computer = null;

		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		cn = computerDatabase.getConnection();

		String req = "select cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cpny.id from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.id=?;";

		st = cn.prepareStatement(req);
		st.setInt(1, id);
		rs = st.executeQuery();

		while (rs.next()) {
			LocalDate localIntro = null;
			LocalDate localDisc = null;
			java.sql.Date sqlIntro = rs.getDate(3);
			java.sql.Date sqlDisc = rs.getDate(4);
			
			if(sqlIntro!=null)
				localIntro = new LocalDate(sqlIntro.getTime());
			if(sqlDisc!=null)
				localDisc = new LocalDate(sqlDisc.getTime());
				
			computer = Computer.builder().id(rs.getLong(1))
					.name(rs.getString(2))
					.introduced(localIntro)
					.discontinued(localDisc)
					.id_company(rs.getLong(5))
					.build();
 
		}

		st.close();
		rs.close();

		log.info("Getting the computer");
		return computer;
	}

	public Long addComputer(String name, LocalDate intro,
			LocalDate disc, int company) throws SQLException {
		log.info("Start inserting a new computer");

		Connection cn = null;
		PreparedStatement st = null;

		cn = computerDatabase.getConnection();

		String req = "insert into computer values(default,?,?,?,?)";

		st = cn.prepareStatement(req);

		st.setString(1, name);
		
		
		st.setDate(2, new java.sql.Date(intro.toDate().getTime()));
		st.setDate(3, new java.sql.Date(disc.toDate().getTime()));

		System.out.println(intro);
		
		if (company > 0) {
			st.setInt(4, company);
		} else {
			st.setNull(4, Types.INTEGER);
		}
		
		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		
		Long key = new Long(0);
		
		if(rs.first())
			key = rs.getLong(1); 
		st.close();

		log.info("Computer has been inserted successfully");
		
		return key;
	}

	public void updateComputer(Long id, String name,
			LocalDate intro, LocalDate disc, int company_id) throws SQLException {
		log.info("Start updating computer");

		Connection cn = null;
		PreparedStatement st = null;

		cn = computerDatabase.getConnection();

		String req = "update computer set name=?, introduced=?, discontinued=?, company_id=? where id =?;";

		st = cn.prepareStatement(req);

		st.setString(1, name);
		st.setDate(2, new java.sql.Date(intro.toDate().getTime()));
		st.setDate(3, new java.sql.Date(disc.toDate().getTime()));

		if (company_id > 0) {
			st.setInt(4, company_id);
		} else {
			st.setNull(4, Types.INTEGER);
		}
		st.setLong(5, id);

		st.executeUpdate();

		st.close();

		log.info("Computer has been updated successfully");
	}

	public void deleteComputer(int id) throws SQLException {
		log.info("Start deleting computer");

		Connection cn = null;
		PreparedStatement st = null;

		cn = computerDatabase.getConnection();

		String req = "delete from computer where id=?;";

		st = cn.prepareStatement(req);

		st.setInt(1, id);

		st.executeUpdate();

		st.close();

		log.info("Computer has been deleted successfully");
	}

	public void setComputerDatabase(ComputerDatabase computerDatabase) {
		this.computerDatabase = computerDatabase;
	}
	
}
