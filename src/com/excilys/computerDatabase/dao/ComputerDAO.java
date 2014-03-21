package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

public class ComputerDAO {

	private static Logger log = LoggerFactory.getLogger(ComputerDAO.class);
	private static ComputerDAO cdao;

	private ComputerDAO() {

	}

	public static ComputerDAO getInstance() {
		if (cdao == null)
			cdao = new ComputerDAO();
		return cdao;
	}

	public ArrayList<Computer> getListComputer(String search,
			String typeOrd, String ord, int page) throws SQLException {
		ArrayList<Computer> computerArray = new ArrayList<>();

		log.info("Start search for computer");

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;

		cn = ComputerDatabase.getInstance().getConnection();

		StringBuilder sb = new StringBuilder();
		sb.append("select cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cpny.name from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.name like '%");
		sb.append(search);
		sb.append("%' or cpny.name like '%");
		sb.append(search);
		sb.append("%'");

		if (typeOrd != null && !typeOrd.equals("") && ord != null	&& !ord.equals("")) {
			sb.append(" order by ");

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
			}
			sb.append(",cpt.name ");
			sb.append(ord);
		}

		sb.append(" limit ");
		sb.append((page - 1) * 15);
		sb.append(",15");

		st = cn.prepareStatement(sb.toString());

		rs = st.executeQuery();

		while (rs.next()) {
			// new Computer
			// (rs.getLong(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5));
			Computer c = Computer.builder().id(rs.getLong(1))
					.name(rs.getString(2)).introduced(rs.getDate(3))
					.discontinued(rs.getDate(4)).company(rs.getString(5))
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

		cn = ComputerDatabase.getInstance().getConnection();

		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) as nbComputer from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.name like '%");
		sb.append(search);
		sb.append("%' or cpny.name like '%");
		sb.append(search);
		sb.append("%'");

		st = cn.prepareStatement(sb.toString());

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

		cn = ComputerDatabase.getInstance().getConnection();

		String req = "select cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cpny.name from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.id=?;";

		st = cn.prepareStatement(req);
		st.setInt(1, id);
		rs = st.executeQuery();

		while (rs.next()) {
			computer = new Computer(rs.getLong(1), rs.getString(2),
					rs.getDate(3), rs.getDate(4), rs.getString(5));
		}

		st.close();
		rs.close();

		log.info("Getting the computer");
		return computer;
	}

	public Long addComputer(String name, Date intro,
			Date disc, int company) throws SQLException {
		log.info("Start inserting a new computer");

		Connection cn = null;
		PreparedStatement st = null;

		cn = ComputerDatabase.getInstance().getConnection();

		String req = "insert into computer values(default,?,?,?,?)";

		st = cn.prepareStatement(req);

		st.setString(1, name);
		st.setDate(2, new java.sql.Date(intro.getTime()));
		st.setDate(3, new java.sql.Date(disc.getTime()));
		
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

	public void updateComputer(int id, String name,
			Date intro, Date disc, int company_id) throws SQLException {
		log.info("Start updating computer");

		Connection cn = null;
		PreparedStatement st = null;

		cn = ComputerDatabase.getInstance().getConnection();

		String req = "update computer set name=?, introduced=?, discontinued=?, company_id=? where id =?;";

		st = cn.prepareStatement(req);

		st.setString(1, name);
		st.setDate(2, new java.sql.Date(intro.getTime()));
		st.setDate(3, new java.sql.Date(disc.getTime()));

		if (company_id > 0) {
			st.setInt(4, company_id);
		} else {
			st.setNull(4, Types.INTEGER);
		}
		st.setInt(5, id);

		st.executeUpdate();

		st.close();

		log.info("Computer has been updated successfully");
	}

	public void deleteComputer(int id) throws SQLException {
		log.info("Start deleting computer");

		Connection cn = null;
		PreparedStatement st = null;

		cn = ComputerDatabase.getInstance().getConnection();

		String req = "delete from computer where id=?;";

		st = cn.prepareStatement(req);

		st.setInt(1, id);

		st.executeUpdate();

		st.close();

		log.info("Computer has been deleted successfully");
	}

}
