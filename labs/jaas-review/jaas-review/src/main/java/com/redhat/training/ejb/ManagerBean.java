package com.redhat.training.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.redhat.training.model.Manager;

@Stateless
public class ManagerBean {
	
	@Inject
	private EntityManager em;
	
	public Manager createManager(Manager m) {
		em.persist(m);
		return m;
	}

	public Manager findById(Long id) {
		return em.find(Manager.class, id);
	}
	
	public void updateManager(Manager m) {
		em.merge(m);
	}
}
