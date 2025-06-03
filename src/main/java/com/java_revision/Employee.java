package com.java_revision;

import java.util.Optional;

public class Employee {
    private String name;
    private int id;
    private int salary;
    private String emailId;
    private String department;

    public Employee(String name, int id, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, int id, int salary, String emailId, String department) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.emailId = emailId;
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Employee(String name, int id, int salary, String emailId) {
        this(name, id, salary);
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int calculateTotalSalary() {
        return this.getSalary();
    }

    @Override
    public String toString() {
         return "Employee [name=" + name + ", id=" + id + ", salary=" + salary + ", emailId="
                + (emailId == null ? "NO_EMAILID_FOUND" : emailId) + ", department=" + department + "]";
    }

    public Optional<String> getEmailIDOptional() {
        return Optional.ofNullable(emailId);
    }
}
