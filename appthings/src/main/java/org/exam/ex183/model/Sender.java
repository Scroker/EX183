package org.exam.ex183.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries(value = { 
		@NamedQuery(name = "getAllSenders", query = "SELECT s FROM Sender s"),
		@NamedQuery(name = "getALlSendersByName", query = "SELECT s FROM Sender s WHERE s.name = :name")
})
public class Sender implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Pattern(regexp = "^(.+)@(\\S+)$", message = "Email is not valid")
	private String email;
	
	@NotNull
	@Size(min = 2, max = 10, message = "Name size must be between 2 and 10")
	private String name;
	
	@OneToMany(mappedBy = "sender")
	private List<GreetingMessage> messages;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<GreetingMessage> getMessages() {
		if (messages == null)
			messages = new ArrayList<GreetingMessage>();
		return messages;
	}

	public void setMessages(List<GreetingMessage> messages) {
		this.messages = messages;
	}
	
}
