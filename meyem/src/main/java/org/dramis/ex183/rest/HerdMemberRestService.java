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

import org.dramis.ex183.model.HerdMember;
import org.dramis.ex183.service.HerdMemberService;

@Path("herd/members")
public class HerdMemberRestService {
	
	@EJB
	private HerdMemberService hms;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<HerdMember> findAllMember() {
		return hms.getAllMembers();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public HerdMember findMember(@PathParam("id") Long id) {
		return hms.getMember(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMember(HerdMember member) {
		ResponseBuilder rb;
		try {
			hms.createMember(member);
			rb = Response.ok();
		} catch (Exception e) {
			rb = Response.serverError();
		}
		return rb.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMember(HerdMember member) {
		ResponseBuilder rb;
		try {
			hms.updateMember(member);
			rb = Response.ok();
		} catch (Exception e) {
			rb = Response.serverError();
		}
		return rb.build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteMember(@PathParam("id") Long id) {
		ResponseBuilder rb;
		try {
			hms.deleteMember(id);
			rb = Response.ok();
		} catch (Exception e) {
			rb = Response.serverError();
		}
		return rb.build();
	}	
}
