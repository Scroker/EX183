package org.exam.ex183.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.exam.ex183.model.Employee;
import org.exam.ex183.model.Team;

@Stateless
public class TeamService {
	
	@Inject
	private EntityManager em;
	
	public void createTeam(Team team) {
		em.persist(team);
	}

	public void updateTeam(Team team) {
		if (em.find(Team.class, team.getId()) != null)
			em.merge(team);
	}
	
	public Team searchTeam(Integer id) {
		return em.find(Team.class, id);
	}
	
	public void addEmployee(Integer teamId, Employee employee) {
		Team team = em.find(Team.class, teamId);
		if (!team.getEmployees().contains(employee)) {
			team.getEmployees().add(employee);
			em.merge(team);
		}
	}
	
	public void removeEmployee(Integer teamId, Employee employee) {
		Team team = em.find(Team.class, teamId);
		if (!team.getEmployees().contains(employee)) {
			team.getEmployees().remove(employee);
			em.merge(team);
		}
	}
	
	public void deleteTeam(Integer id) {
		Team team = em.find(Team.class, id);
		em.remove(team);
	}
	
	public List<Team> listAllTeam() {
		TypedQuery<Team> query = em.createQuery("SELECT t FROM Team t", Team.class);
		return query.getResultList();
	}

	public List<Team> searchTeamByName(String name) {
		TypedQuery<Team> query = em.createNamedQuery("getTeamsByName", Team.class);
		query.setParameter("name", name);
		return query.getResultList();
	}
}
