package org.gdramis.jboss.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Pearson")
@NamedQueries ({
	@NamedQuery(name="getBySurname", query="SELECT p FROM Pearson p WHERE p.surname = :surname"),
	@NamedQuery(name="getByName", query="SELECT p FROM Pearson p WHERE p.name = :name"),
})
public class Pearson implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Non può essere nullo")
	@Size(min=2, message="Deve essere maggiore di due")
	private String name;
	

	@NotNull(message="Non può essere nullo")
	@Size(min=2, message="Deve essere maggiore di due")
	private String surname;
	

	@NotNull(message="Non può essere nullo")
	@Size(min=12, message="Deve essere maggiore di due")
	private String email;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
