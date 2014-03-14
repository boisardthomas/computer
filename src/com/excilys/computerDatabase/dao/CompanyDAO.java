package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerDatabase.bean.Company;
import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.jdbc.ComputerDatabase;
import org.slf4j.*;

public class CompanyDAO {

	Logger log = LoggerFactory.getLogger(CompanyDAO.class);
	
	public ArrayList<Company> getListCompany()
	{
		log.info("start search for company");
		
		ArrayList<Company> companyArray = new ArrayList<>();
		
		try {
			Connection cn = ComputerDatabase.getInstance();
						
			String req = "select * from company;";
			
			PreparedStatement st = cn.prepareStatement(req);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				Company c = new Company (rs.getInt(1),rs.getString(2));
				companyArray.add(c);
			}
			
			st.close();
			rs.close();
			cn=null;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		log.info("end of search for company");
		
		return companyArray;
	}
	
}
