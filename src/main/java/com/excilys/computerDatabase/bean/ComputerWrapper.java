package com.excilys.computerDatabase.bean;

import java.util.ArrayList;

public class ComputerWrapper {

	private Long idComputer;
	private String search;
	private String typeOrd; 
	private String ord; 
	private int page;
	private int nbComputer;
	private ArrayList<Computer> computers;
	
	public ComputerWrapper(){}
	
	public String getSearch() 
	{
		return search;
	}
	
	public void setSearch(String search) 
	{
		this.search = search;
	}
	
	public String getTypeOrd()
	{
		return typeOrd;
	}
	
	public void setTypeOrd(String typeOrd)
	{
		this.typeOrd = typeOrd;
	}
	
	public String getOrd()
	{
		return ord;
	}
	
	public void setOrd(String ord) 
	{
		this.ord = ord;
	}
	
	public int getPage() 
	{
		return page;
	}
	
	public void setPage(int page) 
	{
		this.page = page;
	}
	
	public int getNbComputer() 
	{
		return nbComputer;
	}
	
	public void setNbComputer(int nbComputer) 
	{
		this.nbComputer = nbComputer;
	}	
	
	public Long getIdComputer() {
		return idComputer;
	}

	public void setIdComputer(Long idComputer) 
	{
		this.idComputer = idComputer;
	}

	public ArrayList<Computer> getComputers() 
	{
		return computers;
	}

	public void setComputers(ArrayList<Computer> computers) 
	{
		this.computers = computers;
	}
	
	public static class Builder {

		ComputerWrapper computerWrapper;

		private Builder() {
			computerWrapper = new ComputerWrapper();
		}

		public Builder id(Long id) {
			if(id != null)
				this.computerWrapper.idComputer = id;
			return this;
		}

		public Builder search(String search) {
			if(search != null)
				this.computerWrapper.search = search;
			return this;
		}

		public Builder typeOrd(String typeOrd)
		{
			if(typeOrd != null)
				this.computerWrapper.typeOrd = typeOrd; 
			return this;
		}
		
		public Builder ord(String ord)
		{
			if(ord != null)
				this.computerWrapper.ord = ord;
			return this;
		}
		
		public Builder page(int page)
		{
			this.computerWrapper.page = page;
			return this;
		}
		
		public Builder nbComputer(int nbComputer)
		{
			this.computerWrapper.nbComputer = nbComputer;
			return this;
		}
		
		public Builder computers(ArrayList<Computer> computers)
		{
			if(computers != null)
				this.computerWrapper.computers = computers;
			return this;
		}
		
		public ComputerWrapper build() {
			return this.computerWrapper;
		}

	}

	public static Builder builder() {
		return new Builder();
	}
	
}
