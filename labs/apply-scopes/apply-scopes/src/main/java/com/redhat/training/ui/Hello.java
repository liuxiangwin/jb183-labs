package com.redhat.training.ui;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.redhat.training.ejb.PersonService;

//TODO set the scope

//TODO assign the name "hello" to this EJB

public class Hello implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;

	@Inject
	private PersonService personService;

	public void sayHello() {
		try {
			String response = personService.hello(name);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(response));
		}catch(Exception e){
			System.out.println(e.getCause());
			if(e.getCause() != null && e.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
				String violations = "";
				for(ConstraintViolation<?> cv: ex.getConstraintViolations()) {

					violations += cv.getMessage() + "\n";

					System.out.println("Violations: "+violations);
				}
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(violations));
			}

		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
