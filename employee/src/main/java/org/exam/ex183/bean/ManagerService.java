package org.exam.ex183.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.exam.ex183.model.Employee;
import org.exam.ex183.model.Manager;

@Stateless
public class ManagerService {
	
	@Inject
	private EntityManager em;
	
	public void createManager(Manager manager) {
		em.persist(manager);
	}

	public void updateManager(Manager manager) {
		if (em.find(Employee.class, manager.getId()) != null)
			em.merge(manager);
	}
	
	public Manager searchManager(Integer id) {
		return em.find(Manager.class, id);
	}
	
	public void deleteManager(Integer id) {
		Manager employee = em.find(Manager.class, id);
		em.remove(employee);
	}
	
	public List<Manager> listAllManager() {
		TypedQuery<Manager> query = em.createQuery("SELECT m FROM Manager m", Manager.class);
		return query.getResultList();
	}
	
	

}
