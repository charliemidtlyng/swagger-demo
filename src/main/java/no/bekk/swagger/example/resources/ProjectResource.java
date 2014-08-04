package no.bekk.swagger.example.resources;

import no.bekk.swagger.example.domain.Project;
import no.bekk.swagger.example.service.ProjectService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Produces(APPLICATION_JSON)
@Path("/projects")
@Api(value = "/rest/projects")
public class ProjectResource {

    @Autowired
    ProjectService projectService;

    @GET
    @ApiOperation(
            value = "Find all projects",
            response = Project.class,
            responseContainer = "List"
    )
    public Response allProjects() {
        return Response.ok(projectService.findAllProjects()).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Find project by id",
            response = Project.class
    )
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid project id") })
    public Response findById(@ApiParam(value = "id of project", required = true) @PathParam("id") Long id) {
        Project projectById = projectService.findProjectById(id);
        if (projectById == null) {
            return Response.status(400).build();
        }
        return Response.ok(projectById).build();
    }

    @GET
    @Path("/employees/{employeeId}")
    @ApiOperation(
            value = "Find all projects given an employee",
            response = Project.class,
            responseContainer = "List"
    )
    public Response findByEmployeeId(@ApiParam(value = "id of employee", required = true) @PathParam("employeeId") Long employeeId) {
        return Response.ok(projectService.findProjectsWithEmployee(employeeId)).build();
    }

    @POST
    @ApiOperation(value="Add project")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
    public Response addProject(@ApiParam(value = "A project") Project project) {
        try {
            projectService.addProject(project);
        } catch(IllegalArgumentException iae) {
            return Response.status(405).build();
        }
        return Response.ok(project).build();
    }


    @DELETE
    @Path("/{projectId}")
    @ApiOperation(value="Add project")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "Non-existing project id") })
    public Response deleteProject(@ApiParam(value = "A project id") @PathParam("projectId") Long projectId) {
        try {
            projectService.deleteProject(projectId);
        } catch(IllegalArgumentException iae) {
            return Response.status(405).build();
        }
        return Response.ok().build();
    }

}
