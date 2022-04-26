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
import org.exam.ex183.bean.LogMessageService;
import org.exam.ex183.bean.TeamService;
import org.exam.ex183.model.Employee;
import org.exam.ex183.requestModel.EmployeeRequest;

@Path("employee")
public class EmployeeServiceRest {

	@EJB
	private TeamService teamService;
	
	@EJB
	private EmployeeService employeeService;
	
	@EJB
	private LogMessageService logMessageService;
	
	/* GET FUNCTIONS */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> searchEmployees(@QueryParam("managerId") Integer managerId, @QueryParam("teamId") Integer teamId, @QueryParam("name") String name) {
		logMessageService.writeLog("Porcoddio! funziona allora!");
		if (teamId != null)
			return employeeService.searchEmployeeByTeam(teamId);
		else if (managerId != null)
			return employeeService.searchEmployeeByManager(managerId);
		else if (name != null)
			return employeeService.searchEmployeeByName(name);
		return employeeService.listAllEmployee();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee searchEmployee(@PathParam("id") Integer id) {
		return employeeService.searchEmployee(id);
	}
	
	/* POST FUNCTIONS */
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createEmployee(EmployeeRequest request) {
		Employee employee = new Employee();
		employee.setName(request.getName());
		employee.setSurname(request.getSurname());
		employee.setTeam(teamService.searchTeam(request.getTeamId()));
		employeeService.createEmployee(employee);
	}
	
	/* PUT FUNCTIONS */
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateEmployee(@PathParam("id") Integer id, EmployeeRequest request) {
		Employee employee = employeeService.searchEmployee(id);
		if (request.getName() != null)
			employee.setName(request.getName());
		if (request.getName() != null)
			employee.setSurname(request.getSurname());
		if (request.getTeamId() != null)
			employee.setTeam(teamService.searchTeam(request.getTeamId()));
		employeeService.createEmployee(employee);
	}
	
	/* DELETE FUNCTIONS */
	
	@DELETE
	@Path("{id}")
	public void deleteManager(@PathParam("id") Integer id) {
		Employee employee = employeeService.searchEmployee(id);
		teamService.removeEmployee(id, employee);
		employeeService.deleteEmployee(id);
	}
}
