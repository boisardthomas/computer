package com.excilys.computerDatabase.bean;

import java.util.Date;

public class Computer {

	private Long id;
	private String name;
	private Date introducedDate;
	private Date discontinuedDate;
	private Long id_Company;
	private String Company;

	public Computer()
	{
		
	}
	
	public Computer(Long id, String name, Date introducedDate,
			Date discontinuedDate, String company) {
		super();
		this.id = id;
		this.name = name;
		this.introducedDate = introducedDate;
		this.discontinuedDate = discontinuedDate;
		Company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(Date introducedDate) {
		this.introducedDate = introducedDate;
	}

	public Date getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(Date discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public static class Builder {

		Computer computer;

		private Builder() {
			computer = new Computer();
		}

		public Builder id(Long id) {
			if(id != null)
				this.computer.id = id;
			return this;
		}

		public Builder name(String name) {
			this.computer.name = name;
			return this;
		}

		public Builder introduced(Date introduced) {
			this.computer.introducedDate = introduced;
			return this;
		}

		public Builder discontinued(Date discontinued) {
			this.computer.discontinuedDate = discontinued;
			return this;
		}

		public Builder company(Long id_company) {
			this.computer.id_Company = id_company;
			return this;
		}		
		
		public Builder company(String company) {
			this.computer.Company = company;
			return this;
		}

		public Computer build() {
			return this.computer;
		}

	}

	public Long getId_Company() {
		return id_Company;
	}

	public void setId_Company(Long id_Company) {
		this.id_Company = id_Company;
	}

	public static Builder builder() {
		return new Builder();
	}


}