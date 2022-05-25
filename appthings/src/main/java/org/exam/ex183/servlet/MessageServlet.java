package org.exam.ex183.servlet;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.exam.ex183.exception.ValidationException;
import org.exam.ex183.model.GreetingMessage;
import org.exam.ex183.model.Sender;
import org.exam.ex183.registry.GreetingMessageRegistry;
import org.exam.ex183.registry.SenderRegistry;

@Named
@RequestScoped
public class MessageServlet {

	public final static Logger LOGGER = Logger.getLogger(MessageServlet.class.getName());
	
	@EJB
	private GreetingMessageRegistry mr;

	@EJB
	private SenderRegistry sr;
	
	@Inject
	@JMSConnectionFactory("ConnectionFactory")
	private JMSContext context;
	
    @Resource(mappedName = "java:/jms/queue/MessageQueue")
	private static Queue queue;
	
    @NotNull
	@Size(min = 2, max = 10, message = "Name size must be between 2 and 10")
	private String name;
	
	@NotNull
	@Size(min = 2, max = 30, message = "Email size must be between 2 and 30")
	private String email;
	
	@NotNull
	@Size(min = 10, max = 100, message = "Greeting message size must be between 10 and 100")
	private String message;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void sendMessage() {
		try {
			JMSProducer producer = context.createProducer();
			ObjectMessage objMessage = context.createObjectMessage();
			Sender sender = new Sender();
			GreetingMessage greetingMessage = new GreetingMessage();
			sender.setEmail(this.getEmail());
			sender.setName(this.getName());
			sr.validateSender(sender);
			greetingMessage.setSender(sender);
			greetingMessage.setText(this.getMessage());
			mr.validateMessage(greetingMessage);
			objMessage.setObject(sender);
			producer.send(queue, greetingMessage);
		} catch (ValidationException e) {
			LOGGER.log(Level.WARNING, e.getMessage());		
		} catch (JMSException e) {
		  	LOGGER.log(Level.WARNING, "Message send failed!", e);
		}
	}
	public List<Sender> getSenders() {
		return sr.getAllSenders();
	}
	
	public String getLastMessage(String email) {
		return mr.searchLastMessagesOfSender(email).getText();
	}
}
