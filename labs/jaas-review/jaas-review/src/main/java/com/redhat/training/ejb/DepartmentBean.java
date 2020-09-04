package com.redhat.training.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.redhat.training.model.Department;

@Stateless
public class DepartmentBean {

	@Inject
	private EntityManager em;
	
	public Department createDepartment(Department d) {
		em.persist(d);
		return d;
	}
	
	public Department findById(Long id) {
		return em.find(Department.class, id);
	}
}
