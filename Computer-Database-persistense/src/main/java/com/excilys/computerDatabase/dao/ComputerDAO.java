package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.bean.ComputerRowMapper;

@Repository
public class ComputerDAO {
	
	private Logger log = LoggerFactory.getLogger(ComputerDAO.class);
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Computer> getListComputer(String search,
			String typeOrd, String ord, int page) throws SQLException {
		List<Computer> computerArray = new ArrayList<>();

		log.info("Start search for computer");

		StringBuilder sb = new StringBuilder();
		sb.append("select cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cpny.id, cpny.name from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.name like ? or cpny.name like ?");
		
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
		
		computerArray = jdbcTemplate.query(sb.toString(),new Object[] {"%"+ search +"%", "%" +search + "%"}, new ComputerRowMapper());
				
		log.info("End of search for computer");
		return computerArray;
	}

	public int nbComputer(String search) throws SQLException {
		int nbComputer = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) as nbComputer from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.name like ?");
		sb.append(" or cpny.name like ?");
		
		nbComputer = (Integer)(jdbcTemplate.queryForObject(sb.toString(), new Object[] {"%"+ search +"%", "%" +search + "%"},Integer.class));

		log.info("Return number of computer");
		return nbComputer;
	}

	public Computer getComputer(int id) throws SQLException {
		Computer computer = null;
		String req = "select cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cpny.id, cpny.name as cpny_name from computer as cpt left outer join company as cpny on cpt.company_id=cpny.id where cpt.id=?;";

		List<Computer> computerArray = new ArrayList<>();
		
		computerArray = jdbcTemplate.query(req, new Object[] { id }, new ComputerRowMapper());
		
		if(!computerArray.isEmpty())
			computer = computerArray.get(0);
		
		log.info("Getting the computer");
		return computer;
	}

	public Long addComputer(final Computer computer) throws SQLException {
		log.info("Start inserting a new computer");

		
		final String req = "insert into computer values(default,?,?,?,?)";

		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {           

		     @Override
		     public PreparedStatement createPreparedStatement(Connection connection)
		    		 throws SQLException {
		    	 		PreparedStatement ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
		    	 		ps.setString(1, computer.getName());
		    	 		ps.setDate(2, new java.sql.Date(computer.getIntroducedDate().toDate().getTime()));
		    	 		ps.setDate(3, new java.sql.Date(computer.getDiscontinuedDate().toDate().getTime()));
		    	 		if (computer.getId_Company() > 0) {
		    	 			ps.setLong(4, computer.getId_Company());
		    	 		} else {
		    	 			ps.setNull(4, Types.INTEGER);
		    	 		}
		                    
		    	 		return ps;
		            }
		     }, holder);

		Long key = holder.getKey().longValue();
		
		log.info("Computer" + key +" has been inserted successfully");
		
		return key;
				
	}

	public void updateComputer(Computer computer) throws SQLException {
		log.info("Start updating computer");
		Long company_id = computer.getId_Company();
		if(company_id==0)
			company_id=null;
		
		String req = "update computer set name=?, introduced=?, discontinued=?, company_id=? where id =?;";
		
		Object[] params = {computer.getName(), new java.sql.Date(computer.getIntroducedDate().toDate().getTime()), new java.sql.Date(computer.getDiscontinuedDate().toDate().getTime()), company_id, computer.getId()};
		
		int[] params_type = {Types.VARCHAR, Types.DATE, Types.DATE, Types.BIGINT, Types.BIGINT};		
		
		jdbcTemplate.update(req, params, params_type);

		log.info("Computer has been updated successfully");
	}

	public void deleteComputer(int id) throws SQLException {
		log.info("Start deleting computer");

		String req = "delete from computer where id=?;";

		jdbcTemplate.update(req, new Object[]{id});
		
		log.info("Computer has been deleted successfully");
	}
	
}
