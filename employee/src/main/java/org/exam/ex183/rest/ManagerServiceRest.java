package org.exam.ex183.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.exam.ex183.bean.ManagerService;
import org.exam.ex183.bean.TeamService;
import org.exam.ex183.model.Manager;
import org.exam.ex183.model.Team;
import org.exam.ex183.requestModel.ManagerRequest;

@Path("manager")
public class ManagerServiceRest {

	@EJB
	private ManagerService managerService;

	@EJB
	private TeamService teamService;
	
	/* GET FUNCTIONS */
	
	@GET
	@RolesAllowed({"admins", "users"})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Manager> searchManagers(@QueryParam("name") String name) {
		if (name != null)
			return managerService.searchManagerByName(name);
		return managerService.listAllManager();
	}
	
	@GET
	@Path("{id}")
	@RolesAllowed({"admins", "users"})
	@Produces(MediaType.APPLICATION_JSON)
	public Manager searchManager(@PathParam("id") Integer id) {
		return managerService.searchManager(id);
	}
	
	/* POST FUNCTIONS */
	
	@POST
	@RolesAllowed("admins")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createManager(ManagerRequest request) {
		Manager manager = new Manager();
		manager.setName(request.getName());
		manager.setSurname(request.getSurname());
		manager.setTeam(teamService.searchTeam(request.getTeamId()));
		managerService.createManager(manager);
	}
	
	/* PUT FUNCTIONS */
	@PUT
	@Path("{id}")
	@RolesAllowed("admins")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateManager(@PathParam("id") Integer id, ManagerRequest request) {
		Manager manager = managerService.searchManager(id);
		if(request.getName() != null) 
			manager.setName(request.getName());
		if(request.getSurname() != null)
			manager.setSurname(request.getSurname());
	}
	
	/* DELETE FUNCTIONS */
	
	@DELETE
	@Path("{id}")
	@RolesAllowed("admins")
	public void deleteManager(@PathParam("id") Integer id) {
		Team team = managerService.searchManager(id).getTeam();
		teamService.deleteTeam(team.getId());
		managerService.deleteManager(id);
	}
}
