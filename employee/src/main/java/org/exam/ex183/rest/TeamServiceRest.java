package org.exam.ex183.rest;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.exam.ex183.bean.EmployeeService;
import org.exam.ex183.bean.ManagerService;
import org.exam.ex183.bean.TeamService;
import org.exam.ex183.model.Employee;
import org.exam.ex183.model.Manager;
import org.exam.ex183.model.Team;
import org.exam.ex183.requestModel.TeamRequest;

@Path("team")
public class TeamServiceRest {
	
	@EJB
	private TeamService teamService;

	@EJB
	private ManagerService managerService;

	@EJB
	private EmployeeService employeeService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Team> searchTeams(@QueryParam("name") String name) {
		if (name != null)
			return teamService.searchTeamByName(name);
		return teamService.listAllTeam();
	}

	/* GET FUNCTIONS */
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Team searchTeam(@PathParam("id") Integer id) {
		return teamService.searchTeam(id);
	}
	
	/* POST FUNCTIONS */
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createTeam(TeamRequest request) {
		Team team = new Team();
		team.setName(request.getName());
		team.setManager(managerService.searchManager(request.getManagerId()));
		teamService.createTeam(team);
	}
	
	/* PUT FUNCTIONS */
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void addEmployeeToTeam(@PathParam("id") Integer teamId, @QueryParam("employeeId") Integer employeeId) {
		Employee employee = employeeService.searchEmployee(employeeId);
		teamService.addEmployee(teamId, employee);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void updateTeam(@PathParam("id") Integer teamId, TeamRequest request) {
		Team team = teamService.searchTeam(teamId);
		if (request.getManagerId() != null) {
			Manager manager = managerService.searchManager(request.getManagerId());
			if (!team.getManager().equals(manager)) {
				team.setManager(manager);
			}
		} if (request.getName() != null)
			team.setName(request.getName());
		teamService.updateTeam(team);
	}
	
	/* DELETE FUNCTIONS */
	
	@DELETE
	@Path("{id}")
	public void deleteTeam(@PathParam("id") Integer teamId, @QueryParam("employeeId") Integer employeeId) {
		if (employeeId != null) {
			Employee employee = employeeService.searchEmployee(employeeId);
			teamService.removeEmployee(teamId, employee);
		} else 
			teamService.deleteTeam(teamId);
	}
}
