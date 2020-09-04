package com.redhat.training.todo.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.redhat.training.todo.model.User;

@Stateless
public class UserService {
	
	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;
	
	public void addUser(User u) {
		log.info("Adding new user: " + u.getUsername());
        em.persist(u);
	}

}
