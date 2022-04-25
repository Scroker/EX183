package org.exam.ex183.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.exam.ex183.bean.EmployeeService;
import org.exam.ex183.bean.TeamService;
import org.exam.ex183.model.Employee;
import org.exam.ex183.requestModel.EmployeeRequest;

@Path("employee")
public class EmployeeServiceRest {
	
	@EJB
	private EmployeeService employeeService;

	@EJB
	private TeamService teamService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees(@QueryParam("managerId") Integer managerId, @QueryParam("teamId") Integer teamId, @QueryParam("name") String name) {
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
	public Employee getEmployee(@PathParam("id") Integer id) {
		return employeeService.searchEmployee(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void setEmployee(EmployeeRequest request) {
		Employee employee = new Employee();
		employee.setName(request.getName());
		employee.setSurname(request.getSurname());
		employee.setTeam(teamService.searchTeam(request.getTeamId()));
		employeeService.createEmployee(employee);
	}
}
