package pro.sky.skyprocollectionshw.service;

import java.util.HashMap;

import pro.sky.skyprocollectionshw.data.Employee;

public interface EmployeeService {

    HashMap<String, Employee> getAllEmployees();
    String addEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    String removeEmployee(String firstName, String lastName);
}
