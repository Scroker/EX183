package org.dramis.ex183.service;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.dramis.ex183.model.HerdLeader;

@Singleton
public class HerdLeaderService {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<HerdLeader> getAllLeaders() {
		TypedQuery<HerdLeader> query = em.createQuery("select hm from HerdLeader hm", HerdLeader.class);
		return query.getResultList();
	}
	
	public HerdLeader getLeader(Long id) {
		HerdLeader hm = em.find(HerdLeader.class, id);
		return hm;
	}
	
	public void createLeader(HerdLeader hm) {
		em.persist(hm);
	}
	
	public void updateLeader(HerdLeader hm) {
		em.merge(hm);
	}
	
	public void deleteLeader(Long id) {
		HerdLeader member = em.find(HerdLeader.class, id);
		em.detach(member);
	}
}
