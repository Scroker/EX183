package org.exam.ex183.servlet;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("welcome")
public class WelcomeBean {
	
	public String getHello() {
		return "Hello Employee";
	}

}
