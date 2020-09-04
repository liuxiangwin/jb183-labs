package com.redhat.training.services;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.redhat.training.model.Member;

@Stateless

public class MemberService {
    
	@PersistenceContext(unitName="member-unit")
	private EntityManager entityManager;
	
	public String saveMember(String emailId) {
		try {
				
				Member member = new Member();
				member.setEmailId(emailId);
																		
				return "Congratulations ! you will get our weekly Tech Letter at " + emailId ;
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}

	
	public List<Member> getAllmembers() {
		TypedQuery<Member> query = entityManager.createQuery("SELECT m FROM Member m", Member.class);
		List<Member> members = query.getResultList();

		return members;
	}

}
