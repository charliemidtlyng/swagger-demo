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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Produces(APPLICATION_JSON)
@Path("/projects")
public class ProjectResource {

    @Autowired
    ProjectService projectService;

    @GET
    public Response allProjects() {
        return Response.ok(projectService.findAllProjects()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Project projectById = projectService.findProjectById(id);
        if (projectById == null) {
            return Response.status(400).build();
        }
        return Response.ok(projectById).build();
    }

    @GET
    @Path("/employees/{employeeId}")
    public Response findByEmployeeId(@PathParam("employeeId") Long employeeId) {
        return Response.ok(projectService.findProjectsWithEmployee(employeeId)).build();
    }

    @POST
    public Response addProject(Project project) {
        try {
            projectService.addProject(project);
        } catch (IllegalArgumentException iae) {
            return Response.status(405).build();
        }
        return Response.ok(project).build();
    }


    @DELETE
    @Path("/{projectId}")
    public Response deleteProject(@PathParam("projectId") Long projectId) {
        try {
            projectService.deleteProject(projectId);
        } catch (IllegalArgumentException iae) {
            return Response.status(405).build();
        }
        return Response.ok().build();
    }

}
