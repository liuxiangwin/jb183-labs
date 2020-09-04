package com.redhat.training.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.redhat.training.messaging.JMSClient;
import com.redhat.training.model.Person;

@Stateless
public class PersonService {
	
	@Inject
	JMSClient jmsUtil;

	// Simple non-RESTy method for JSF bean invocation
	public String hello(String name) {

		// let's grab the current date and time on the server
		LocalDateTime today = LocalDateTime.now();

		// format it nicely for on-screen display
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a");
		String fdate = today.format(format);

		// Create a new Person object and persist to database
		Person p = new Person();
		p.setName(name);

		// Send a JMS message to the 'helloWorldQueue'
		jmsUtil.sendMessage("Said Hello to " + name.toUpperCase() + " at " + fdate);

		// respond back with Hello and convert the name to UPPERCASE. Also, send the
		// current time on the server.
		return "Hello " + name.toUpperCase() + "!. " + "Time on the server is: " + fdate;

	}
	
	public String getMessage() {
		String msg = jmsUtil.getMessage();
		if(msg != null) {
			return msg;
		}else {
			return "Queue is empty!";
		}
	} 

}
