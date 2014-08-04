package no.bekk.swagger.example.service;

import no.bekk.swagger.example.domain.Project;

import java.util.List;

public interface ProjectService {

    public List<Project> findAllProjects();
    public Project findProjectById(Long id);
    public List<Project> findProjectsWithEmployee(Long employeeId);

    void addProject(Project project);

    void deleteProject(Long projectId);

}
