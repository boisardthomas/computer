package com.excilys.computerDatabase.dto;

import java.util.HashMap;

public class ComputerDTO {

	private String nom;
	private String introduced;
	private String discontinued;
	private int company;
	
	public ComputerDTO(String nom, String introduced, String discontinued, int company) {
		super();
		this.nom = nom;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
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
