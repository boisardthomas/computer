package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;

import org.slf4j.*;

public enum CompanyDAO {

	INSTANCE;
	
	private static Logger log = LoggerFactory.getLogger(CompanyDAO.class);
	
	public ArrayList<Company> getListCompany(Connection cndb) throws SQLException
	{
		log.info("start search for company");
		
		ArrayList<Company> companyArray = new ArrayList<>();
		
		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;		
		
		cn = ComputerDatabase.getInstance().getConnection();
					
		String req = "select * from company;";
			
		st = cn.prepareStatement(req);
			
		rs = st.executeQuery();
			
		while(rs.next())
		{
			Company c = Company.builder().id(rs.getLong(1)).name(rs.getString(2)).build();
			companyArray.add(c);
		}
		
		st.close();
		rs.close();
		
				
		log.info("end of search for company");
		
		return companyArray;
	}

	public Company getCompany(long l) throws SQLException
	{
		log.info("start search for company");
		
		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;	
		
		cn = ComputerDatabase.getInstance().getConnection();
		
		String req = "select * from company where id="+l;
		
		st = cn.prepareStatement(req);
		
		rs = st.executeQuery();
		
		Company cpn = null;
		
		if(rs.next())
			cpn = Company.builder().id(rs.getLong(1)).name(rs.getString(2)).build();
			
		st.close();
		rs.close();
		
		return cpn;
	}
	
}
