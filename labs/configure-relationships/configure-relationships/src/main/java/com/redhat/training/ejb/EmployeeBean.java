package com.redhat.training.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.redhat.training.model.Department;

@Stateless
public class EmployeeBean {

	@Inject
	private EntityManager em;
	
	public List<Department> getAllDepartments(){
		TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d" , Department.class);
    	
        return query.getResultList();
	}
	

}
