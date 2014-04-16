package com.excilys.computerDatabase.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.LocalDate;
import org.springframework.jdbc.core.RowMapper;

public class ComputerRowMapper implements RowMapper<Computer>{

	@Override
	public Computer mapRow(ResultSet rs, int arg1) throws SQLException {
		
		LocalDate localIntro = null;
		LocalDate localDisc = null;
		java.sql.Date sqlIntro = rs.getDate(3);
		java.sql.Date sqlDisc = rs.getDate(4);
		
		if(sqlIntro!=null)
			localIntro = new LocalDate(sqlIntro.getTime());
		if(sqlDisc!=null)
			localDisc = new LocalDate(sqlDisc.getTime());
			
		Computer computer = Computer.builder().id(rs.getLong(1))
				.name(rs.getString(2))
				.introduced(localIntro)
				.discontinued(localDisc)
				.id_company(rs.getLong(5))
				.company(rs.getString(6))
				.build();
		
		
		return computer;
	}

}
