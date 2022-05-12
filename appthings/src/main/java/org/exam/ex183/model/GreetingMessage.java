package org.exam.ex183.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@NamedQueries(value = { 
		@NamedQuery(name = "getAllMessages", query = "SELECT m FROM GreetingMessage m"),
		@NamedQuery(name = "getAllMessagesBySender", query = "SELECT m FROM GreetingMessage m WHERE m.sender.email = :email"),
		@NamedQuery(name = "getLastMessagesOfSender", query = "SELECT m FROM GreetingMessage m WHERE m.sender.email = :email AND m.creationTimestamp = (SELECT max(m.creationTimestamp) FROM GreetingMessage m WHERE m.sender.email = :email)")
})
public class GreetingMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min = 10, max = 100, message = "Greeting message size must be between 10 and 100")
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "senderId")
	private Sender sender;

	@CreationTimestamp
	private Timestamp creationTimestamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Timestamp getCreationTimestamp() {
		return creationTimestamp;
	}
}
