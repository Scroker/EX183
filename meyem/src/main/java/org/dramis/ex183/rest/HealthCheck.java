package org.dramis.ex183.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("healthcheck")
public class HealthCheck {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String checkStatus() {
		return "{ status : \"OK\" }";
	}
	
}
