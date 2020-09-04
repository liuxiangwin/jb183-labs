package com.redhat.training.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.redhat.training.model.Person;

@Stateless
//TODO Add a transaction manage type of 'BEAN'

public class PersonService {

	@PersistenceContext
	private EntityManager entityManager;

	//TODO Inject a UserTransaction to be used by this EJB
	@Resource


	public String hello(String name) {
		try {
			//TODO start a new transaction

			// let's grab the current date and time on the server
			LocalDateTime today = LocalDateTime.now();

			// format it nicely for on-screen display
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a");
			String fdate = today.format(format);

			// Create a new Person object and persist to database
			Person p = new Person();
			p.setName(name);
			entityManager.persist(p);

			//TODO commit the transaction


			// respond back with Hello and convert the name to UPPERCASE. Also, send the
			// current time on the server.
			return "Hello " + name.toUpperCase() + "!. " + "Time on the server is: " + fdate;
		} catch(Exception e) {
			//TODO roll-back the transaction

			throw new EJBException(e);
		}
	}
}
