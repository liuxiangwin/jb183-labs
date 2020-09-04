package com.redhat.training.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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

	//TODO add role based permissions to permit all authenticated users
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

	@GET
	@Path("getByManager/{managerId}")
	@Produces(MediaType.APPLICATION_XML)
	public List<Employee> getEmployeesForManager(@PathParam("managerId")Long managerId){
		Manager manager = managerBean.findById(managerId);
		return employeeBean.findAllForManager(manager);
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

	//TODO add role based permissions to permit all manager and superuser users
	@POST
	@Path("assignEmployee/{employeeId}/{departmentId}")
	public void assignEmployee(@PathParam("employeeId")Long employeeId, @PathParam("departmentId") Long departmentId) {
		Employee e = employeeBean.readEmployeeById(employeeId);
		Department d = departmentBean.findById(departmentId);
		e.setDepartment(d);
		employeeBean.updateEmployee(e);
	}

	//TODO add role based permissions to permit all superuser users
	@POST
	@Path("assignManager/{managerId}/{departmentId}")
	public void assignManager(@PathParam("managerId")Long managerId, @PathParam("departmentId") Long departmentId) {
		Manager m = managerBean.findById(managerId);
		Department d = departmentBean.findById(departmentId);
		m.setDepartment(d);
		managerBean.updateManager(m);
	}

}
