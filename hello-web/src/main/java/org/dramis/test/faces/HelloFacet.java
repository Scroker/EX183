package org.dramis.test.faces;

import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named("helloFacet")
public class HelloFacet {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
