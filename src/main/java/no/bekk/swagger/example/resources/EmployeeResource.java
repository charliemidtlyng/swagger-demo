package no.bekk.swagger.example.resources;

import no.bekk.swagger.example.domain.Employee;
import no.bekk.swagger.example.service.EmployeeService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static no.bekk.swagger.example.domain.Employee.Departement;

@Component
@Produces(APPLICATION_JSON)
@Path("/employees")
@Api(value = "/rest/employees")
public class EmployeeResource {

    @Autowired private EmployeeService employeeService;

    @GET
    @ApiOperation(
            value = "Find all employees",
            response = Employee.class,
            responseContainer = "List")
    public Response allEmployees() {
        return Response.ok(employeeService.all()).build();
    }

    @GET
    @Path("/findByStatus/{departement}")
    @ApiOperation(
            value = "Find employees working within a specific departement",
            response = Employee.class,
            responseContainer = "List",
            notes = "Find an employee")
    public Response getEmployeesByDepartement(
            @ApiParam(value = "departement of the employees to be fetched", required = true,
                    allowableValues = "Administration,Technology,Design,BMC,Other")
            @PathParam("departement") Departement departement) {
        return Response.ok(employeeService.findByDepartement(departement)).build();
    }

}
