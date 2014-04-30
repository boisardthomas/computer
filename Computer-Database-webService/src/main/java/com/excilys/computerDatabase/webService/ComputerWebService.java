package com.excilys.computerDatabase.webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.service.ComputerService;

@Component
@Path("/computerService")
public class ComputerWebService {

	@Autowired
	private ComputerService computerService;
	
	@GET
	@Path("/getComputers")	
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll()
	{
		return Response.status(200).entity(computerService.findAll()).build();
	}
	
}
