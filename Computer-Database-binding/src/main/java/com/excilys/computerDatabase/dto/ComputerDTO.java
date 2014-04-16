package com.excilys.computerDatabase.dto;

import javax.persistence.Entity;

@Entity
public class ComputerDTO {

	private Long id;
	private String name;	
	private String introduced;
	private String discontinued;
	private long company;   
	
	public ComputerDTO()
	{
		
	}
	
	public ComputerDTO(Long id,String nom, String introduced, String discontinued, long company) {
		super();
		this.id = id;
		this.name = nom;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCompany() {
		return company;
	}

	public void setCompany(long company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	
	@Override
	public String toString() {
		return "ComputerDTO [nom=" + name + ", introduced=" + introduced
				+ ", discontinued=" + discontinued + "]";
	}
		
}
