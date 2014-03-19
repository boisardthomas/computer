package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerDatabase.bean.Company;
import org.slf4j.*;

public class CompanyDAO {

	private static Logger log = LoggerFactory.getLogger(CompanyDAO.class);
	
	private static CompanyDAO cdao;
	
	private CompanyDAO()
	{
		
	}
	
	public static CompanyDAO getInstance()
	{
		if(cdao == null)
			cdao = new CompanyDAO();
		return cdao;
	}
	
	public ArrayList<Company> getListCompany(Connection cndb) throws SQLException
	{
		log.info("start search for company");
		
		ArrayList<Company> companyArray = new ArrayList<>();
		
		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;		
		
		cn = cndb;
					
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
	
}
