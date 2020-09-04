package com.redhat.training.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.redhat.training.model.Person;

@Stateless
@Path("persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@TransactionManagement(TransactionManagementType.BEAN)
public class PersonService {

	@PersistenceContext
	private EntityManager entityManager;

	@Resource
	UserTransaction tx;



	// CRUD RESTful methods below

	// fetch result by Person id
	//TODO permit all users
	@GET
	@Path("{id}")
	public Person getPerson(@PathParam("id") Long id) {
		return entityManager.find(Person.class, id);
		
	}

	// Dump all Person objects in the Database
	//TODO allow only guest
	@GET
	public List<Person> getAllPersons() {
		TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p", Person.class);
		List<Person> persons = query.getResultList();

		return persons;
	}

	// delete an object by Person id
	//TODO restrict access for all roles
	@DELETE
    @Path("{id}")
    public void deletePerson(@PathParam("id") Long id) {
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
	//TODO allow only admin
	@POST
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
