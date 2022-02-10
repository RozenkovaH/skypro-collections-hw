package pro.sky.skyprocollectionshw.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.exception.EmployeeAlreadyExistsException;
import pro.sky.skyprocollectionshw.exception.EmployeeNotFoundException;
import pro.sky.skyprocollectionshw.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static String KEY_TEMPLATE = "%s|%s";
    private final Map<String, Employee> employees = new HashMap<>();

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public String addEmployee(String firstName, String lastName, int department, int salary) {
        Employee newEmployee = new Employee(firstName, lastName, department, salary);
        doesEmployeeExist(newEmployee);
        employees.put(employeeToKey(newEmployee), newEmployee);
        return "Employee <b>" + firstName + " " + lastName + "</b> has been successfully added.";
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        return employees.get(getEmployeeKeyOrError(firstName, lastName));
    }

    @Override
    public String removeEmployee(String firstName, String lastName) {
        employees.remove(getEmployeeKeyOrError(firstName, lastName));
        return "Employee <b>" + firstName + " " + lastName + "</b> has been successfully fired.";
    }

    private String employeeToKey(Employee employee) {
        return employeeToKey(employee.getFirstName(), employee.getLastName());
    }

    private String employeeToKey(String firstName, String lastName) {
        return String.format(KEY_TEMPLATE, firstName, lastName);
    }

    private void doesEmployeeExist(Employee employee) {
        if (employees.containsKey(employeeToKey(employee))) {
            throw new EmployeeAlreadyExistsException();
        }
    }

    private String getEmployeeKeyOrError(String firstName, String lastName) {
        String key = employeeToKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return key;
        }
        throw new EmployeeNotFoundException();
    }
}
