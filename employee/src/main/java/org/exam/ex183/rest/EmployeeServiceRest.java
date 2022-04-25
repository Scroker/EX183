package org.exam.ex183.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.exam.ex183.bean.EmployeeService;
import org.exam.ex183.model.Employee;

@Path("employee")
public class EmployeeServiceRest {
	
	@EJB
	private EmployeeService employeeService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAllEmployee() {
		return employeeService.listAllEmployee();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam("id") Integer id) {
		return employeeService.searchEmployee(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployee(@QueryParam("name") String name) {
		return employeeService.searchEmployeeByName(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployeeByTeam(@QueryParam("teamId") Integer teamId ) {
		return employeeService.searchEmployeeByTeam(teamId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployeeByManager(@QueryParam("managerId") Integer managerId ) {
		return employeeService.searchEmployeeByManager(managerId);
	}
}
