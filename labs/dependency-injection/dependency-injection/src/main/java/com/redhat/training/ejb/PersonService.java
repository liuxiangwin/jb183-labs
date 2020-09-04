package com.redhat.training.ejb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.redhat.training.model.Person;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PersonService {

	//TODO inject NameUtil

	@PersistenceContext
	private EntityManager entityManager;

	@Resource
	UserTransaction tx;

	// Simple non-RESTy method for JSF bean invocation
	public String hello(String name) {
		try {
			try {
				// start a new transaction
				tx.begin();

				// let's grab the current date and time on the server
				LocalDateTime today = LocalDateTime.now();

				// format it nicely for on-screen display
				DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a");
				String fdate = today.format(format);
				System.out.println("###Before NameUtil method used###");

				//TODO sanitize name

				// Create a new Person object and persist to database
				Person p = new Person();
				p.setName(name);
				entityManager.persist(p);

				// respond back with Hello. Also, send the
				// current time on the server.
				return "Hello " + name + "!. " + "Time on the server is: " + fdate;
			} finally {
				// commit the transaction
				tx.commit();
			}
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
			try {
				tx.begin();
				entityManager.remove(getPerson(id));
			} finally {
				tx.commit();
			}
		} catch (Exception e) {
			throw new EJBException();
		}
    }

	// Save a Person object to Database
	public Response savePerson(Person person) {
		try {
			try {
			ResponseBuilder builder;
			if (person.getId() == null) {
				Person newPerson = new Person();
				newPerson.setName(person.getName());
				tx.begin();
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
			}finally {
				tx.commit();
			}
		}catch (Exception e) {
			throw new EJBException(e);
		}
	}

}
