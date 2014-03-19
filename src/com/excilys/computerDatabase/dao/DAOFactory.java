package com.excilys.computerDatabase.dao;

public class DAOFactory {

	private static DAOFactory df;
	
	private DAOFactory()
	{
		
	}

	public DAOFactory getInstance()
	{
		if(df==null)
			df = new DAOFactory();
		return df;
	}
	
	public CompanyDAO getCompanyDAO()
	{
		return CompanyDAO.getInstance();
	}
	
	public ComputerDAO getComputerDAO()
	{
		return ComputerDAO.getInstance();
	}
	
	public LogDAO getLogDAO()
	{
		return LogDAO.getInstance();
	}
	
}
