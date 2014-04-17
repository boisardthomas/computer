package com.excilys.computerDatabase.bean;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.LocalDate;

@Entity
@Table(name="computer")
public class Computer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="introduced")
	private LocalDate introducedDate;
	@Column(name="discontinued")
	private LocalDate discontinuedDate;
	@Column(name="company_id")
	private Company company;

	public Computer()
	{
		company=new Company();
	}
	
	public Computer(Long id, String name, LocalDate introducedDate,
			LocalDate discontinuedDate, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introducedDate = introducedDate;
		this.discontinuedDate = discontinuedDate;
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introducedDate="
				+ introducedDate + ", discontinuedDate=" + discontinuedDate
				+ ", Company=" + company + "]";
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

	public LocalDate getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(LocalDate introducedDate) {
		this.introducedDate = introducedDate;
	}

	public LocalDate getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(LocalDate discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public String getCompany() {
		return company.getName();
	}

	public void setCompany(String comp) {
		company.setName(comp);
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

		public Builder introduced(LocalDate introduced) {
			this.computer.introducedDate = introduced;
			return this;
		}

		public Builder discontinued(LocalDate discontinued) {
			this.computer.discontinuedDate = discontinued;
			return this;
		}

		public Builder id_company(Long id_company) {
			this.computer.company.setId(id_company);
			return this;
		}		
		
		public Builder company(String company) {
			this.computer.company.setName(company);
			return this;
		}

		public Computer build() {
			return this.computer;
		}

	}

	public Long getId_Company() {
		return company.getId();
	}

	public void setId_Company(Long id_Company) {
		this.company.setId(id_Company);
	}

	public static Builder builder() {
		return new Builder();
	}


}
