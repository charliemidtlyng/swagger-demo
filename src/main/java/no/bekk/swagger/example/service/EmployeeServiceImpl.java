package no.bekk.swagger.example.service;

import no.bekk.swagger.example.domain.Employee;
import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;
import static no.bekk.swagger.example.domain.Employee.Departement;
import static no.bekk.swagger.example.domain.Employee.Departement.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public static List<Employee> dummyEmployees = createDummyEmployees();

    private static List<Employee> createDummyEmployees() {
        Employee manuel = createEmployee(1, "Manuel", 35, 2000000, BMC, null);
        Employee philipp = createEmployee(2, "Philipp", 45, 500000, Technology, null);
        Employee jerome = createEmployee(3, "Jerome", 27, 400000, Technology, philipp);
        Employee mats = createEmployee(4, "Mats", 45, 800000, Administration, null);
        Employee benedikt = createEmployee(5, "Benedikt", 25, 2000000, BMC, manuel);
        Employee christoph = createEmployee(6, "Christoph", 25, 2000000, BMC, manuel);
        Employee bastian = createEmployee(7, "Bastian", 25, 2000000, Design, benedikt);
        Employee toni = createEmployee(8, "Toni", 25, 2000000, BMC, manuel);
        Employee thomas = createEmployee(9, "Thomas", 25, 2000000, Technology, philipp);
        Employee mesut = createEmployee(10, "Mesut", 25, 2000000, Other, mats);
        Employee miroslav = createEmployee(11, "Miroslav", 25, 2000000, Design, mats);
        return asList(manuel, philipp, jerome, mats, benedikt, christoph, bastian, toni, thomas, mesut, miroslav);
    }

    private static Employee createEmployee(long id, String name, int age, double salary, Departement departement, Employee supervisor) {
        Employee employee = new Employee();
        employee.id = id;
        employee.name = name;
        employee.age = age;
        employee.salary = salary;
        employee.departement = departement;
        employee.supervisor = supervisor != null ? supervisor.id : null;
        return employee;
    }

    public List<Employee> findByDepartement(Departement departement) {
        List<Employee> employees = new ArrayList<Employee>();
        if (departement == null) {
            return employees;
        }

        for (Employee employee : dummyEmployees) {
            if (departement.equals(employee.departement)) {
                employees.add(employee);
            }
        }

        return employees;
    }

    public List<Employee> all() {
        return dummyEmployees;
    }
}
