package org.exam.ex183.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.exam.ex183.bean.TeamService;
import org.exam.ex183.model.Team;

@Path("team")
public class TeamServiceRest {
	
	@EJB
	private TeamService teamService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Team> getTeams(@QueryParam("name") String name) {
		if (name != null)
			return teamService.searchTeamByName(name);
		return teamService.listAllTeam();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Team getTeam(@PathParam("id") Integer id) {
		return teamService.searchTeam(id);
	}
}
