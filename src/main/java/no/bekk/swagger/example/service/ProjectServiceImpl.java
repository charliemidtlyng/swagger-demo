package no.bekk.swagger.example.service;


import no.bekk.swagger.example.domain.Employee;
import no.bekk.swagger.example.domain.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    public static List<Project> dummyProjects = createDummyProjects();

    @Override
    public List<Project> findAllProjects() {
        return dummyProjects;
    }

    @Override
    public Project findProjectById(Long id) {
        for (Project project : dummyProjects) {
            if (project.id == id) {
                return project;
            }
        }

        return null;
    }

    @Override
    public List<Project> findProjectsWithEmployee(Long employeeId) {
        List<Project> projectsWithEmployee = new ArrayList<Project>();
        for (Project project : dummyProjects) {
            for (Employee member : project.members) {
                if (member.id == employeeId) {
                    projectsWithEmployee.add(project);
                    continue;
                }
            }
        }

        return projectsWithEmployee;
    }

    @Override
    public void addProject(Project project) {
        if (project.id == null) {
            throw new IllegalArgumentException("Must have valid id");
        }
        dummyProjects.add(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        if (projectId == null) {
            throw new IllegalArgumentException("Must have valid id");
        }

        for (Project project : dummyProjects) {
            if(project.id == projectId) {
                dummyProjects.remove(project);
                return;
            }
        }
        throw new IllegalArgumentException("Id does not exist");
    }

    private static List<Project> createDummyProjects() {
        List<Employee> dummyEmployees = EmployeeServiceImpl.dummyEmployees;

        Project tine = createDummyProject(1,
                "TINE",
                Arrays.asList(dummyEmployees.get(0), dummyEmployees.get(1), dummyEmployees.get(2), dummyEmployees.get(6)),
                dummyEmployees.get(3));
        Project nav = createDummyProject(2,
                "NAV",
                Arrays.asList(dummyEmployees.get(4), dummyEmployees.get(5), dummyEmployees.get(1)),
                dummyEmployees.get(6));

        ArrayList<Project> projects = new ArrayList<Project>();
        projects.add(tine);
        projects.add(nav);
        return projects;
    }

    private static Project createDummyProject(long id, String name, List<Employee> members, Employee kam) {
        Project project = new Project();
        project.id = id;
        project.name = name;
        project.members = members;
        project.kam = kam;
        return project;
    }
}
