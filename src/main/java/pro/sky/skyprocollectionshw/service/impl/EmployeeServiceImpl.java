package pro.sky.skyprocollectionshw.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.exception.EmployeeAlreadyExistsException;
import pro.sky.skyprocollectionshw.exception.EmployeeNotFoundException;
import pro.sky.skyprocollectionshw.service.EmployeeFormatterService;
import pro.sky.skyprocollectionshw.service.EmployeeService;
import pro.sky.skyprocollectionshw.service.ValidationService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final ValidationService validationService;
    private final EmployeeFormatterService employeeFormatterService;

    public EmployeeServiceImpl(ValidationService validationService,
                               EmployeeFormatterService employeeFormatterService) {
        this.validationService = validationService;
        this.employeeFormatterService = employeeFormatterService;
    }

    private final static String KEY_TEMPLATE = "%s|%s";
    private final Map<String, Employee> employees = new HashMap<>();

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(getEmployees().values());
    }

    @Override
    public String addEmployee(Employee employee) {
        validationService.validateEmployee(employee);
        employee = employeeFormatterService.format(employee);
        doesEmployeeExist(employee);
        employees.put(employeeToKey(employee), employee);
        return "Employee <b>" + employee.getFirstName() + " " + employee.getLastName() + "</b> has been successfully added.";
    }

    @Override
    public Employee findEmployee(Employee employee) {
        return employees.get(getEmployeeKeyOrError(employeeFormatterService.format(employee)));
    }

    @Override
    public String removeEmployee(Employee employee) {
        employees.remove(getEmployeeKeyOrError(employeeFormatterService.format(employee)));
        return "Employee <b>" + employee.getFirstName() + " " + employee.getLastName() + "</b> has been successfully fired.";
    }

    protected Map<String, Employee> getEmployees() {
        return employees;
    }

    private String employeeToKey(Employee employee) {
        return String.format(KEY_TEMPLATE, employee.getFirstName(), employee.getLastName());
    }

    private void doesEmployeeExist(Employee employee) {
        if (employees.containsKey(employeeToKey(employee))) {
            throw new EmployeeAlreadyExistsException();
        }
    }

    private String getEmployeeKeyOrError(Employee employee) {
        String key = employeeToKey(employee);
        if (employees.containsKey(key)) {
            return key;
        }
        throw new EmployeeNotFoundException();
    }
}
