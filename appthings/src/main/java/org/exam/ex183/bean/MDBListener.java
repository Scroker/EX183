package org.exam.ex183.bean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.exam.ex183.model.GreetingMessage;
import org.exam.ex183.registry.GreetingMessageRegistry;
import org.exam.ex183.registry.SenderRegistry;

@MessageDriven(name = "MessageListener",  activationConfig = {
	@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/MessageQueue"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MDBListener implements MessageListener {

	@EJB
	private GreetingMessageRegistry mr;

	@EJB
	private SenderRegistry sr;
	
	private final static Logger LOGGER = Logger.getLogger(MDBListener.class.getName());
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = null;
		try {
			if (message instanceof ObjectMessage) {
				objectMessage = (ObjectMessage) message;
				GreetingMessage greetingMessage = (GreetingMessage) objectMessage.getObject();	
				sr.createSender(greetingMessage.getSender());
				mr.createMessage(greetingMessage);
			}  else {
				LOGGER.log(Level.WARNING,"Incorrect Message Type!");
			}
		}
		catch (JMSException e) {
			LOGGER.log(Level.WARNING, "JMS exception!", e);
		}
	}

}
