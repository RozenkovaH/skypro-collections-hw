package pro.sky.skyprocollectionshw.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.service.DepartmentService;
import pro.sky.skyprocollectionshw.service.EmployeeService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees()
                .stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllEmployeesByDept(int department) {
        return employeeService.getAllEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Employee findEmployeeWithMaxSalaryInDept(int department) {
        return getAllEmployeesByDept(department).stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee findEmployeeWithMinSalaryInDept(int department) {
        return getAllEmployeesByDept(department).stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }
}
