package org.dramis.ex183.rest;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.dramis.ex183.model.HerdLeader;
import org.dramis.ex183.model.HerdMember;
import org.dramis.ex183.service.HerdLeaderService;
import org.dramis.ex183.service.HerdMemberService;

@Path("herd/service")
public class HerdService {

	@EJB
	private HerdLeaderService hls;
	
	@EJB
	private HerdMemberService hms;
	
	@POST
	@Path("promoteLeader/{id}")
	public Response promoteLeader(@PathParam("id") Long id) {	
		ResponseBuilder rb;
		try {
			HerdMember member = hms.getMember(id);
			HerdLeader leader = new HerdLeader();
			leader.setName(member.getName());
			leader.setSurname(member.getSurname());
			leader.setPhoneNumber(member.getPhoneNumber());
			leader.setEmail(member.getEmail());
			hls.createLeader(leader);
			hms.deleteMember(id);
			rb = Response.ok();
		} catch (Exception e) {
			rb = Response.serverError();
		}
		return rb.build();
	}
	
	@POST
	@Path("assignLeader/{memberId}/{leaderId}")
	public Response promoteLeader(@PathParam("memberId") Long memberId, @PathParam("leaderId") Long leaderId) {	
		ResponseBuilder rb;
		try {
			HerdMember member = hms.getMember(memberId);
			HerdLeader leader = hls.getLeader(leaderId);
			leader.getUnderlings().add(member);
			hls.updateLeader(leader);
			member.setLeader(leader);
			hms.updateMember(member);
			rb = Response.ok();
		} catch (Exception e) {
			rb = Response.serverError();
		}
		return rb.build();
	}
}
