package org.exam.ex183.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.exam.ex183.bean.ManagerService;
import org.exam.ex183.model.Manager;

@Path("manager")
public class ManagerServiceRest {

	@EJB
	private ManagerService managerService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Manager> getAllManagers() {
		return managerService.listAllManager();
	}
	
}
