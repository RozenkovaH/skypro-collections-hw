package pro.sky.skyprocollectionshw.service;

import java.util.Collection;

import pro.sky.skyprocollectionshw.data.Employee;

public interface EmployeeService {

    Collection<Employee> getAllEmployees();
    String addEmployee(String firstName, String lastName, int department, int salary);
    Employee findEmployee(String firstName, String lastName);
    String removeEmployee(String firstName, String lastName);
}
