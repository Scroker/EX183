package org.exam.ex183.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

}
