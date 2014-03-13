package com.excilys.computerDatabase.bean;

import java.util.Date;

public class Computer {

	private int id;
	private String name;
	private Date introducedDate;
	private Date discontinuedDate;
	private String Company;
	
	public Computer(int id, String name, Date introducedDate,
			Date discontinuedDate, String company) {
		super();
		this.id = id;
		this.name = name;
		this.introducedDate = introducedDate;
		this.discontinuedDate = discontinuedDate;
		Company = company;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	
	
}
