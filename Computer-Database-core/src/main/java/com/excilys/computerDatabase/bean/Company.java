package com.excilys.computerDatabase.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Company")
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public Long id;
	@Column(name="name")
	public String name;
	
	public Company(){}
	
	public Company(Long id, String name)
	{
		this.id = id;
		this.name = name;
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
	
	public static class Builder {

		Company company;

		private Builder() {
			company = new Company();
		}

		public Builder id(Long id) {
			if(id != null)
				this.company.id = id;
			return this;
		}

		public Builder name(String name) {
			this.company.name = name;
			return this;
		}

		public Company build() {
			return this.company;
		}

	}

	public static Builder builder() {
		return new Builder();
	}
}
