package org.exam.ex183.bean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.exam.ex183.model.LogMessage;
import org.exam.ex183.resource.PersistenceContextLog;

@Stateless
public class LogMessageService {

	@Inject
	@PersistenceContextLog
	private EntityManager em;
	
	public void writeLog(String message) {
		LogMessage logMessage = new LogMessage();
		logMessage.setText(message);
		em.persist(logMessage);
	}
	
}
