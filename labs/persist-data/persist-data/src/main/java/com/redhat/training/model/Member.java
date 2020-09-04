package com.redhat.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;


public class Member implements Serializable{

    private Long id;

    private String emailId;

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
    	return emailId;
    }

    public void setEmailId(String emailId) {
    	this.emailId=emailId;
    }

}
