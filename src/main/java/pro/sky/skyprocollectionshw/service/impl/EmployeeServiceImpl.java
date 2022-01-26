package pro.sky.skyprocollectionshw.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.exception.EmployeeAlreadyExistsException;
import pro.sky.skyprocollectionshw.exception.EmployeeNotFoundException;
import pro.sky.skyprocollectionshw.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public HashMap<String, Employee> getAllEmployees() {
        return new HashMap<>(employees);
    }

    @Override
    public String addEmployee(String firstName, String lastName) throws EmployeeAlreadyExistsException {
        Employee newEmployee = new Employee(firstName, lastName);
        doesEmployeeExist(newEmployee);
        employees.put(UUID.randomUUID().toString(), newEmployee);
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

    private void doesEmployeeExist(Employee employee) {
        for (Employee value: employees.values()) {
            if (value.equals(employee)) {
                throw new EmployeeAlreadyExistsException();
            }
        }
    }

    private String getEmployeeKeyOrError(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        for (Map.Entry<String, Employee> item : employees.entrySet()) {
            if (item.getValue().equals(employee)) {
                return item.getKey();
            }
        }
        throw new EmployeeNotFoundException();
    }
}
