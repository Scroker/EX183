package org.dramis.ex183.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;

@Entity
@XmlRootElement
public class HerdLeader implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 2, max = 10)
	private String name;
	
	@NotNull
	@Size(min = 2, max = 10)
	private String surname;
	
	@NotNull
	@Digits(integer = 10, fraction = 0)
	private BigInteger phoneNumber;
	
	@NotNull
	@Email
	private String email;
	
	@OneToMany(mappedBy = "leader", fetch = FetchType.EAGER)
	private List<HerdMember> underlings;

	public Long getId() {
		return id;
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

	public BigInteger getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(BigInteger phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<HerdMember> getUnderlings() {
		return underlings;
	}

	public void setUnderlings(List<HerdMember> underlings) {
		this.underlings = underlings;
	}
}
