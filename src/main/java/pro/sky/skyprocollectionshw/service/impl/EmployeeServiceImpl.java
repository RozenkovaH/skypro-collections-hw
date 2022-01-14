package pro.sky.skyprocollectionshw.service.impl;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.exception.EmployeeAlreadyExistsException;
import pro.sky.skyprocollectionshw.exception.EmployeeNotFoundException;
import pro.sky.skyprocollectionshw.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> employees = new LinkedList<>();

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public String addEmployee(String firstName, String lastName) throws EmployeeAlreadyExistsException {
        Employee newEmployee = new Employee(firstName, lastName);
        doesEmployeeExist(newEmployee);
        employees.add(newEmployee);
        return "Employee <b>" + firstName + " " + lastName + "</b> has been successfully added.";
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        return employees.get(getEmployeeIndexOrError(firstName, lastName));
    }

    @Override
    public String removeEmployee(String firstName, String lastName) {
        employees.remove(getEmployeeIndexOrError(firstName, lastName));
        return "Employee <b>" + firstName + " " + lastName + "</b> has been successfully fired.";
    }

    private void doesEmployeeExist(Employee employee) {
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyExistsException();
        }
    }

    private int getEmployeeIndexOrError(String firstName, String lastName) throws EmployeeNotFoundException {
        int index = getEmployeeIndex(firstName, lastName);
        if (index >= 0) {
            return index;
        }
        throw new EmployeeNotFoundException();
    }

    private int getEmployeeIndex(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employees.indexOf(employee);
        }
        return -1;
    }
}
