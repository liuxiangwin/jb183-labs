package com.redhat.training.ui;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.redhat.training.ejb.Magic8BallBean;

//TODO Make @RequestScoped
//TODO Name "eightBall"
public class EightBall {

	private String question;

	//TODO Inject this EJB

	Magic8BallBean eightBallEJB;

	public String ask() {
		return eightBallEJB.answerQuestion(question);
	}
	public void answerQuestion() {
		String response = ask();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(response));
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
