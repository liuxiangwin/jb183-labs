package com.redhat.training.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.redhat.training.ejb.EmployeeBean;
import com.redhat.training.model.Department;
import com.redhat.training.model.Employee;
import com.redhat.training.model.Manager;

@Named("departmentView")
@Stateless
@RequestScoped
public class DepartmentViewBean {
	
	private List<Department> departments;

	private Department currentDepartment;
	
	private Manager manager;
	
	private List<Employee> employees;
	
	@Inject
	private Logger log;

	@Inject
	EmployeeBean peopleBean;
	
	@PostConstruct
	public void init() {
		log.info("INIT!!!");
		departments = peopleBean.getAllDepartments();
		
	}

	public void update(ValueChangeEvent event) {
		
		log.info("Attempting to match: "+ event.getNewValue());
				
		for(Department department: departments) {
			if(department.equals(event.getNewValue())) {
				manager = department.getManager();
				employees = new ArrayList<Employee>(department.getEmployees());
				log.info("Match!");
				break;
			}
		}
		
		
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}


	public Department getCurrentDepartment() {
		return currentDepartment;
	}

	public void setCurrentDepartment(Department currentDepartment) {
		this.currentDepartment = currentDepartment;
	}

	

}
