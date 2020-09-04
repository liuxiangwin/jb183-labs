package com.redhat.training.ui;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.transaction.SystemException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.redhat.training.service.PersonService;

import javax.inject.Inject;

@RequestScoped
@Named("hello")
public class Hello {
	private String name;

	@Inject
	private PersonService personService;

	public void sayHello() throws IllegalStateException, SecurityException, SystemException {
		String response = personService.hello(name);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(response));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
