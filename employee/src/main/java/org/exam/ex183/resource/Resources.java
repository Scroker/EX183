package org.exam.ex183.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class Resources {
	
	@Produces
	@PersistenceContextEmployee
	@PersistenceContext(unitName = "employee")
	private EntityManager employeeEm;

	@Produces
	@PersistenceContextLog
	@PersistenceContext(unitName = "log")
	private EntityManager logEm;
}
