package org.test.dramis.bean;

import javax.ejb.Stateful;

@Stateful
//@Startup
public class RememberMyNameBean {

	private String myName;

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}
}
