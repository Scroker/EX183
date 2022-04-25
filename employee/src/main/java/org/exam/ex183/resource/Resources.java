package org.exam.ex183.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class Resources {
	
	@Inject
	@PersistenceContext
	private EntityManager em;
	
}
