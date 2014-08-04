package no.bekk.swagger.example.resources;

import no.bekk.swagger.example.service.EmployeeService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static no.bekk.swagger.example.domain.Employee.Departement;

@Component
@Produces(APPLICATION_JSON)
@Path("/employees")
public class EmployeeResource {

    @Autowired private EmployeeService employeeService;

    @GET
    public Response allEmployees() {
        return Response.ok(employeeService.all()).build();
    }

    @GET
    @Path("/findByStatus/{departement}")
    public Response getEmployeesByDepartement(@PathParam("departement") Departement departement) {
        return Response.ok(employeeService.findByDepartement(departement)).build();
    }

}
