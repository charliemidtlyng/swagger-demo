package no.bekk.swagger.example.service;

import no.bekk.swagger.example.domain.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findByDepartement(Employee.Departement departement);
    public List<Employee> all();
}
