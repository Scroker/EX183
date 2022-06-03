package org.dramis.ex183.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.dramis.ex183.model.HerdLeader;
import org.dramis.ex183.service.HerdLeaderService;

@Path("herd/leaders")
public class HerdLeaderRestService {
	
	@EJB
	private HerdLeaderService hls;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<HerdLeader> findAllMember() {
		return hls.getAllLeaders();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public HerdLeader findLeader(@PathParam("id") Long id) {
		return hls.getLeader(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createLeader(HerdLeader leader) {
		ResponseBuilder rb;
		try {
			hls.createLeader(leader);
			rb = Response.ok();
		} catch (Exception e) {
			rb = Response.serverError();
		}
		return rb.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateLeader(HerdLeader leader) {
		ResponseBuilder rb;
		try {
			hls.updateLeader(leader);
			rb = Response.ok();
		} catch (Exception e) {
			rb = Response.serverError();
		}
		return rb.build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteLeader(@PathParam("id") Long id) {
		ResponseBuilder rb;
		try {
			hls.deleteLeader(id);
			rb = Response.ok();
		} catch (Exception e) {
			rb = Response.serverError();
		}
		return rb.build();
	}
}
