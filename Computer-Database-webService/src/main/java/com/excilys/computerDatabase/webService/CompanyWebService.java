package com.excilys.computerDatabase.webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.service.CompanyService;

@Component
@Path("/companyService")
public class CompanyWebService {

	@Autowired
	private CompanyService companyService;
	
	@GET
	@Path("/getCompanies")	
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll()
	{
		return Response.status(200).entity(companyService.getListCompany()).build();
	}
}
