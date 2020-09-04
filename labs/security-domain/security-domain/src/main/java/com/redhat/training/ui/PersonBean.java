package com.redhat.training.ui;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.redhat.training.model.Person;
import com.redhat.training.rest.PersonService;

@RequestScoped
@Named("personBean")
public class PersonBean {
	private Long id;
	private String name;

	@Inject
	private PersonService personService;

	public void hello() {
		try {
			String response = personService.hello(name);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(response));
		} catch (Exception e) {
			System.out.println(e.getCause());
			if (e.getCause() != null && e.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
				String violations = "";
				for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {

					violations += cv.getMessage() + "\n";

					System.out.println("Violations: " + violations);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void getPerson() {
		try {
			Person foundPerson = personService.getPerson(id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(foundPerson.getName()));
		} catch (Exception e) {
			System.out.println(e.getCause());
			if (e.getCause() != null && e.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
				String violations = "";
				for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {

					violations += cv.getMessage() + "\n";

					System.out.println("Violations: " + violations);
				}
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(violations));
			}

		}

	}

	public List<Person> getPersons() {
		return personService.getAllPersons();
	}

}