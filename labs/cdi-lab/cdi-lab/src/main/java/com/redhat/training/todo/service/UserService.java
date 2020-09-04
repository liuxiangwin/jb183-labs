package com.redhat.training.todo.service;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.redhat.training.todo.model.User;

//TODO make ItemService stateless
public class UserService {

	//TODO add annotation for logger
	private Logger log;

	//TODO add annotation for entitymanager
	private EntityManager em;

	public void addUser(User u) {
		log.info("Adding new user: " + u.getUsername());
    em.persist(u);
	}

}
