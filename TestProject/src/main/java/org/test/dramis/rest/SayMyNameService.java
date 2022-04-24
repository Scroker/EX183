package org.test.dramis.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.test.dramis.bean.SayMyNameBean;

@Path("sayMyNameService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SayMyNameService {
	
	@EJB
	SayMyNameBean myNameBean;
	
	@GET
	@Path("sayMyName")
	public Response getSayMyNameService(@QueryParam("name") String name) {
		Response.ResponseBuilder builder = null;
		myNameBean.setMyName(name);
	    builder = Response.ok();
        return builder.build();
	}
	
}
