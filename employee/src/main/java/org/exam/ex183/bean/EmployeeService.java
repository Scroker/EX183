package org.exam.ex183.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.exam.ex183.model.Employee;

@Stateless
public class EmployeeService {
	
	@Inject
	private EntityManager em;
	
	public void createEmployee(Employee employee) {
		em.persist(employee);
	}

	public void updateEmployee(Employee employee) {
		if (em.find(Employee.class, employee.getId()) != null)
			em.merge(employee);
	}
	
	public Employee searchEmployee(Integer id) {
		return em.find(Employee.class, id);
	}
	
	public void deleteEmployee(Integer id) {
		Employee employee = em.find(Employee.class, id);
		em.remove(employee);
	}
	
	public List<Employee> listAllEmployee() {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
		return query.getResultList();
	}

	public List<Employee> searchEmployeeByName(String name) {
		TypedQuery<Employee> query = em.createNamedQuery("getEmployeesByName", Employee.class);
		query.setParameter("name", name);
		return query.getResultList();
	}

	public List<Employee> searchEmployeeByTeam(Integer teamId) {
		TypedQuery<Employee> query = em.createNamedQuery("getEmployeesByTeam", Employee.class);
		query.setParameter("teamId", teamId);
		return query.getResultList();
	}
	
	public List<Employee> searchEmployeeByManager(Integer managerId) {
		TypedQuery<Employee> query = em.createNamedQuery("getEmployeesByManager", Employee.class);
		query.setParameter("managerId", managerId);
		return query.getResultList();
	}

}
