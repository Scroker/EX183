package org.gdramis.jboss.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.gdramis.jboss.model.Pearson;

@Stateless
public class PearsonService{
   
	@Inject
	private EntityManager em;

	public void registerItem(Pearson pearson) throws Exception {
		em.persist(pearson);
	}

	public Pearson findById(Long id) throws Exception {
		return em.find(Pearson.class, id);
	}

	public List<Pearson> listBySurname(String surname) throws Exception {
		TypedQuery<Pearson> query = em.createNamedQuery("getBySurname", Pearson.class);
		query.setParameter("surname", surname);
		return query.getResultList();
	}

	public List<Pearson> listByName(String name) throws Exception {
		TypedQuery<Pearson> query = em.createNamedQuery("getByName", Pearson.class);
		query.setParameter("name", name);
		return query.getResultList();
	}
	
	public List<Pearson> listAll() throws Exception {
		TypedQuery<Pearson> query = em.createQuery("SELECT p FROM Pearson p" , Pearson.class);
		return query.getResultList();
	}
	
	public List<Pearson> findByMail(String email) throws Exception {
		TypedQuery<Pearson> query = em.createQuery("SELECT p FROM Pearson p WHERE p.email = ?0" , Pearson.class);
		query.setParameter(0, email);
		return query.getResultList();
	}
	
	public void removeItem(Long id) throws Exception {
		em.remove(this.findById(id));
	}
   
	public void updateItem(Pearson pearson) {
		em.merge(pearson);
	}
}