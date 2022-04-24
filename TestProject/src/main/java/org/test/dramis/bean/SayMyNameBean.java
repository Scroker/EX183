package org.test.dramis.bean;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class SayMyNameBean {
	
	private String myName;
	
	@PostConstruct
	public void constructFunction() {
		System.out.println("Hello!");
	}
	
	public String getMyName() {
		return this.myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

}
