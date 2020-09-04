package com.redhat.training.ejb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.redhat.training.model.Person;

//TODO mark as a stateless EJB

//TODO assign the name "personService" to this EJB

public class PersonService {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	HelloCounterBean counter;

	// Simple non-RESTy method for JSF bean invocation
	public String hello(String name) {
		try {

			// let's grab the current date and time on the server
			LocalDateTime today = LocalDateTime.now();

			// format it nicely for on-screen display
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a");
			String fdate = today.format(format);

			// Create a new Person object and persist to database
			Person p = new Person();
			p.setName(name);
			entityManager.persist(p);

			counter.add();
			// respond back with Hello and convert the name to UPPERCASE. Also, send the
			// current time on the server.
			return "Hello " + name + "!. " + "Time on the server is: " + fdate;
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}

	// CRUD RESTful methods below

	// fetch result by Person id
	public Person getPerson(Long id) {
		return entityManager.find(Person.class, id);
	}

	// Dump all Person objects in the Database
	public List<Person> getAllPersons() {
		TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p", Person.class);
		List<Person> persons = query.getResultList();

		return persons;
	}

	public void deletePerson(Long id) {
		try {

			entityManager.remove(getPerson(id));

		} catch (Exception e) {
			throw new EJBException();
		}
	}

	// Save a Person object to Database
	public Response savePerson(Person person) {
		try {
				ResponseBuilder builder;
				if (person.getId() == null) {
					Person newPerson = new Person();
					newPerson.setName(person.getName());
					entityManager.persist(newPerson);
					builder = Response.ok();
				} else {
					Person uPerson;
					Person updatePerson = getPerson(person.getId());
					updatePerson.setName(person.getName());
					uPerson = entityManager.merge(updatePerson);
					builder = Response.ok(uPerson);
				}

				return builder.build();
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}

}
