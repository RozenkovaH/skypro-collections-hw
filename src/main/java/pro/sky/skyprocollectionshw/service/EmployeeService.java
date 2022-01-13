package pro.sky.skyprocollectionshw.service;

import pro.sky.skyprocollectionshw.data.Employee;

public interface EmployeeService {

    String collectAllEmployeesAsString();
    String addEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    String removeEmployee(String firstName, String lastName);
}
