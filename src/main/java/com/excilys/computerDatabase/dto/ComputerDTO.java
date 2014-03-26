package com.excilys.computerDatabase.dto;

public class ComputerDTO {

	private String nom;
	private String introduced;
	private String discontinued;
	private long company;
	
	public ComputerDTO(String nom, String introduced, String discontinued, long company) {
		super();
		this.nom = nom;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public long getCompany() {
		return company;
	}

	public void setCompany(long company) {
		this.company = company;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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
		return "ComputerDTO [nom=" + nom + ", introduced=" + introduced
				+ ", discontinued=" + discontinued + "]";
	}
		
}