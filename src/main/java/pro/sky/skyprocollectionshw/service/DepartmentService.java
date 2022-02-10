package pro.sky.skyprocollectionshw.service;

import java.util.Collection;

import pro.sky.skyprocollectionshw.data.Employee;

public interface DepartmentService {

    Collection<Employee> getAllEmployees();
    Collection<Employee> getAllEmployeesByDept(int department);
    Employee findEmployeeWithMaxSalaryInDept(int department);
    Employee findEmployeeWithMinSalaryInDept(int department);
}
