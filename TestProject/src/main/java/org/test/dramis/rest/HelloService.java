package org.test.dramis.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("helloService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloService {
	
	@GET
	@Path("sayHello")
	public String getHelloQuery(@DefaultValue("World") @QueryParam("name") String name) {
		return "Hello " + name + "!";
	}
	
	@GET
	@Path("sayHello/{name}")
	public String getHelloParam(@PathParam("name") String name) {
		return "Hello " + name + "!";
	}
	
}
