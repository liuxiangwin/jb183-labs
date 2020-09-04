package com.redhat.training.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.redhat.training.ejb.DepartmentBean;
import com.redhat.training.ejb.EmployeeBean;
import com.redhat.training.ejb.ManagerBean;
import com.redhat.training.model.Department;
import com.redhat.training.model.Employee;
import com.redhat.training.model.Manager;

@Stateless
@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRestService {

	@Inject
	private EmployeeBean employeeBean;

	@Inject
	private DepartmentBean departmentBean;

	@Inject
	private ManagerBean managerBean;

	@GET
	@Path("/byId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam("id") Long id) {
		return employeeBean.readEmployeeById(id);
	}

	@DELETE
	@Path("/byId/{id}")
	public void deleteEmployee(@PathParam("id") Long id) {
		Employee toBeDeleted = employeeBean.readEmployeeById(id);
		employeeBean.deleteEmployee(toBeDeleted);
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
	public Response saveEmployee(Employee employee) {

		ResponseBuilder builder;
		if (employee.getId() == null) {

			Employee newEmployee = new Employee();
			newEmployee.setName(employee.getName());

			employeeBean.createEmployee(newEmployee);

			builder = Response.ok();
		} else {

			Employee employeeToUpdate = employeeBean.readEmployeeById(employee.getId());

			if (employeeToUpdate == null) {
				builder = Response.serverError();
			} else {
				employeeBean.updateEmployee(employee);
				builder = Response.ok();
			}
		}

		return builder.build();
	}

	@POST
	@Path("createDepartment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Department createDepartment(Department d) {
		return departmentBean.createDepartment(d);
	}

	@POST
	@Path("createManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Manager createManager(Manager m) {
		return managerBean.createManager(m);
	}

	//TODO add method to pull an XML list of employees for a given manager

	//TODO add REST method to assign an employee to a department by ID

	//TODO add REST method to assign a manager to a department by ID

}
