package org.exam.ex183.registry;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.exam.ex183.exception.ValidationException;
import org.exam.ex183.model.GreetingMessage;

@Stateless
public class GreetingMessageRegistry {
	
	@Inject
	private EntityManager em;
	
	public void createMessage(GreetingMessage message) {
		em.persist(message);
	}
	
	public GreetingMessage searchMessage(Integer messageId) {
		return em.find(GreetingMessage.class, messageId);
	}

	public List<GreetingMessage> getAllMessages() {
		TypedQuery<GreetingMessage> query = em.createNamedQuery("getAllMessages", GreetingMessage.class);
		return query.getResultList();
	}
	
	public GreetingMessage searchLastMessagesOfSender(String email) {
		TypedQuery<GreetingMessage> query = em.createNamedQuery("getLastMessagesOfSender", GreetingMessage.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}
	
	public List<GreetingMessage> searchMessagesBySender(String email) {
		TypedQuery<GreetingMessage> query = em.createNamedQuery("getAllMessagesBySender", GreetingMessage.class);
		query.setParameter("email", email);
		return query.getResultList();
	}
	
	public void validateMessage(GreetingMessage message) throws ValidationException { 
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<GreetingMessage>> constraintViolations = validator.validate(message);
		for (ConstraintViolation<GreetingMessage> contraints : constraintViolations) {
			throw new ValidationException(contraints.getMessage());
		}
	}
}
