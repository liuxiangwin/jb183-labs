package com.redhat.training.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

//TODO make application scoped

//TODO make a singleton

//TODO assign the name "counter" to this EJB

public class HelloCounterBean {

	private int currentCount;

	@PostConstruct
	public void setup() {
		setCurrentCount(0);
	}

	public void add() {
		setCurrentCount(getCurrentCount() + 1);
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

}
