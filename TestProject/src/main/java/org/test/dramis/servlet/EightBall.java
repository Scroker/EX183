package org.test.dramis.servlet;

// import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
// import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.test.dramis.bean.Magic8BallBean;
import org.test.dramis.bean.RememberMyNameBean;
import org.test.dramis.bean.SayMyNameBean;

//TODO Make @RequestScoped
//TODO Name "eightBall"
@RequestScoped
// @SessionScoped
@Named("eightBall")
public class EightBall /* implements Serializable */ {

	// private static final long serialVersionUID = 1L;

	private String question;

	//TODO Inject this EJB
	@EJB
	Magic8BallBean eightBallEJB;
	
	@EJB
	SayMyNameBean myNameBean;

	@EJB
	RememberMyNameBean nameBean;


	public void answerQuestion() {
		String response = eightBallEJB.answerQuestion(question);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(response));
	}

	public String getName() {
		return nameBean.getMyName();
	}

	public void setName(String name) {
		nameBean.setMyName(name);
	}
	
	public String getMyName() {
		return myNameBean.getMyName();
	}

	public void setMyName(String name) {
		myNameBean.setMyName(name);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}