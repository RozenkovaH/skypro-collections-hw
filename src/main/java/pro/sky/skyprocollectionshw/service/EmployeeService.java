package pro.sky.skyprocollectionshw.service;

import java.util.Collection;

import pro.sky.skyprocollectionshw.data.Employee;

public interface EmployeeService {

    Collection<Employee> getAllEmployees();
    String addEmployee(Employee employee);
    Employee findEmployee(Employee employee);
    String removeEmployee(Employee employee);
}
