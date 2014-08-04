package no.bekk.swagger.example.domain;

public class Employee {

    public Long id;
    public String name;
    public Integer age;
    public Double salary;
    public Departement departement;
    public Long supervisor;

    public static enum Departement {
        Administration,
        Technology,
        Design,
        BMC,
        Other
    }
}