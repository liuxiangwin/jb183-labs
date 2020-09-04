package com.redhat.training.ui;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.redhat.training.service.PersonService;

@RequestScoped
@Named("hello")
public class Hello {

	private String message;

	private String name;

	@Inject
	private PersonService personService;

	public void sayHello() {
		String response = personService.hello(name);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(response));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
