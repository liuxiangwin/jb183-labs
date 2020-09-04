package com.redhat.training.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.redhat.training.model.Employee;
import com.redhat.training.model.Manager;
import com.redhat.training.util.EmployeeLogger;
import com.redhat.training.util.EmployeeLogger.Operation;

@Stateless
public class EmployeeBean {

	//TODO inject EntityManager

	@Inject
	private EmployeeLogger logger;

	public void createEmployee(Employee e) {
		//TODO persist employee

		logger.logAction(e, Operation.Create);
	}

	public Employee readEmployeeById(Long id) {

		//TODO find the employee by its ID
		Employee employee = null;
		logger.logAction(employee, Operation.Read);
		return employee;
	}

	public void updateEmployee(Employee e) {
		//TODO merge the employee record

		logger.logAction(e, Operation.Update);
	}

	public void deleteEmployee(Employee e) {
		//TODO delete the employee

		logger.logAction(e, Operation.Delete);
	}

	public List<Employee> findAllForManager(Manager manager) {
		//TODO use the named query to find all the employees for a manager

		return null;
	}
}
