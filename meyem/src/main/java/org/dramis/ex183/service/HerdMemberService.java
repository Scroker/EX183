package org.dramis.ex183.service;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.dramis.ex183.model.HerdMember;

@Singleton
public class HerdMemberService {

	@PersistenceContext
	private EntityManager em;
	
	public List<HerdMember> getAllMembers() {
		TypedQuery<HerdMember> query = em.createQuery("select hm from HerdMember hm", HerdMember.class);
		return query.getResultList();
	}
	
	public HerdMember getMember(Long id) {
		HerdMember hm = em.find(HerdMember.class, id);
		return hm;
	}
	
	public void createMember(HerdMember hm) {
		em.persist(hm);
	}
	
	public void updateMember(HerdMember hm) {
		em.merge(hm);
	}
	
	public void deleteMember(Long id) {
		HerdMember member = em.find(HerdMember.class, id);
		em.remove(member);
	}
}
