package org.dramis.ex183.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("math")
public class AritmeticService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("multiply/{first}/{second}")
	public String multiply(@PathParam("first") Integer a, @PathParam("second") Integer b) {
		Integer result = a * b;
		return "{ result : " + result + " }";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("divide/{first}/{second}")
	public String divide(@PathParam("first") Integer a, @PathParam("second") Integer b) {
		Integer result = a/b;
		return "{ result : " + result + " }";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("sum")
	public String sum(@QueryParam("first") Integer a, @QueryParam("second") Integer b) {
		Integer result = a + b;
		return "{ result : " + result + " }";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("subtract")
	public String subtract(@QueryParam("first") Integer a, @QueryParam("second") Integer b) {
		Integer result = a-b;
		return "{ result : " + result + " }";
	}
}
