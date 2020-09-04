package com.redhat.training.ui;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.redhat.training.model.Member;
import com.redhat.training.services.MemberService;

import javax.inject.Inject;
import java.util.List;

@RequestScoped
@Named("memberBean")
public class MemberBean {
	private Long id;
	private String emailId;

	@Inject
	private MemberService memberService;

	public void register() {
		try {
			String response = memberService.saveMember(emailId);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(response));
		}catch(Exception e){
			System.out.println(e.getCause());
			if(e.getCause() != null && e.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
				String violations = "";
				for(ConstraintViolation<?> cv: ex.getConstraintViolations()) {
					
					violations += cv.getMessage() + "\n";
					
					System.out.println("Violations: "+violations);
				}
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(violations));
			}
			
		}
		
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Member> getMembers() {
		return memberService.getAllmembers();
		
	}
	
	
	

}
