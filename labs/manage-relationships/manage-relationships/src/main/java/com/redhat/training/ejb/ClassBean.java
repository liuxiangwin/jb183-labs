package com.redhat.training.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.redhat.training.model.Classroom;

@Stateless
public class ClassBean {

	@Inject
	private EntityManager em;
	
	public List<Classroom> getAllClassrooms(){
		TypedQuery<Classroom> query = em.createQuery("SELECT c FROM Classroom c" , Classroom.class);
    	
        return query.getResultList();
	}
	

}
