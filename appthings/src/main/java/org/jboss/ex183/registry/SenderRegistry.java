package org.jboss.ex183.registry;

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
import org.exam.ex183.model.Sender;

@Stateless
public class SenderRegistry {

	@Inject
	private EntityManager em;
	

	public void createSender(Sender sender) {
		if (em.find(Sender.class, sender.getEmail()) == null)
			em.persist(sender);
	}
	
	public Sender searchSender(String email) {
		return em.find(Sender.class, email);
	}

	public List<Sender> getAllSenders() {
		TypedQuery<Sender> query = em.createNamedQuery("getAllSenders", Sender.class);
		return query.getResultList();
	}
	
	public void validateSender(Sender sender) throws ValidationException{ 
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Sender>> constraintViolations = validator.validate(sender);
		for (ConstraintViolation<Sender> constraint : constraintViolations) {
			throw new ValidationException(constraint.getMessage());
		}
	}
}
