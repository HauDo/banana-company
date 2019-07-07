package com.banana.center.employee.endpoint;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.banana.center.employee.model.Employee;
import com.banana.center.employee.model.Employees;
import com.banana.center.employee.repository.EmployeeRepository;
import com.banana.center.enumeration.Status;
import com.banana.center.policy.repository.PolicyRepository;

@Path("employee")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class EmployeeEndpoint {

    private EmployeeRepository employeeRepository;

    private PolicyRepository policyRepository = new PolicyRepository();
    
    public EmployeeEndpoint(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    @GET
    @Path("")
    public Response getAllEmployees(
    		@QueryParam("account") String account,
    		@QueryParam("localtime") String localtime,
    		@QueryParam("id") String id,
    		@QueryParam("status") String status) {
    	
    	Employees list = employeeRepository.list();

        if (null != id) {
            return Response.accepted(list.filterById(id).filterOutInactive()).build();
        }
        if (null != account) {
            list = list.filterByAccount(account);
        }
        if (null != localtime) {
            list = list.filterByLocaltime(localtime);
        }
        if (null != status) {
            list = list.filterByStatus(status);
        }

        return Response.accepted(list.filterOutInactive()).build();
    }
    
    @POST
    @Path("{id}/checkin/{policy}")
    public Response checkin(
    		@PathParam("account") String account,
    		@PathParam("policy") String policy) {

        final Employees matchingEmployees = employeeRepository.list().filterByAccount(account);

        if (null == account
        		|| null == policy
        		|| matchingEmployees.isEmpty()
        		|| policyRepository.findById(policy).isEmpty()
        		|| matchingEmployees.first().getStatus() != Status.UNKNOWN) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not process this request ").build();
        }

        matchingEmployees.first().checkin();

        return Response.accepted(matchingEmployees.first()).build();
    }

    @POST
    @Path("{id}/checkout")
    public Response checkout(
    		@PathParam("account") String account) {

        final Employees matchingEmployees = employeeRepository.list().filterByAccount(account);

        if (null == account
        		|| matchingEmployees.isEmpty()
        		|| matchingEmployees.first().getStatus() != Status.CHECKED_IN) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not process this request").build();
        }

        matchingEmployees.first().checkout();

        return Response.accepted(matchingEmployees.first()).build();
    }

    @POST
    @Path("{id}/inactive")
    public Response inactiveEmployee(
    		@PathParam("account") String account) {

        final Employees matchingEmployees = employeeRepository.list().filterByAccount(account);

        if (null == account
        		|| matchingEmployees.isEmpty()
        		|| matchingEmployees.first().getStatus() == Status.INACTIVE) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not process this request").build();
        }

        matchingEmployees.first().inactive();

        return Response.accepted(matchingEmployees.first()).build();
    }

    @POST
    @Path("{id}/active")
    public Response activeEmployee(
    		@PathParam("account") String account) {
    	
    	final Employees matchingEmployees = employeeRepository.list().filterByAccount(account);

        if (null == account
        		|| matchingEmployees.isEmpty()
        		|| matchingEmployees.first().getStatus() != Status.INACTIVE) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not process this request").build();
        }

        matchingEmployees.first().active();

        return Response.accepted(matchingEmployees.first()).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("")
    public Response addNewEmployee(
            @FormParam("account") String account) {

    	employeeRepository.add(new Employee(account, Status.UNKNOWN));

        return Response.accepted(employeeRepository.list().size()).build();
    }

}