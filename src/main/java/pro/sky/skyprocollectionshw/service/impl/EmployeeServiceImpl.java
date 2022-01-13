package pro.sky.skyprocollectionshw.service.impl;

import org.springframework.stereotype.Service;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.exception.EmployeeAlreadyExistsException;
import pro.sky.skyprocollectionshw.exception.EmployeeNotFoundException;
import pro.sky.skyprocollectionshw.exception.NoFreeSlotsInArrayException;
import pro.sky.skyprocollectionshw.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Employee[] employees = new Employee[3];

    @Override
    public String collectAllEmployeesAsString() {
        StringBuilder employeesList = new StringBuilder();

        for (Employee employee : employees) {
            if (employee != null) {
                employeesList.append(employee).append("<br>");
            }
        }
        return employeesList.toString().isEmpty() ? "There are no employees in the array." : employeesList.toString();
    }

    @Override
    public String addEmployee(String firstName, String lastName) throws EmployeeAlreadyExistsException {
        doesEmployeeExist(firstName, lastName);
        employees[findFirstFreeIndex()] = new Employee(firstName, lastName);
        return "Employee <b>" + firstName + " " + lastName + "</b> has been successfully added.";
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        return employees[getEmployeeIndexOrError(firstName, lastName)];
    }

    @Override
    public String removeEmployee(String firstName, String lastName) {
        employees[getEmployeeIndexOrError(firstName, lastName)] = null;
        return "Employee <b>" + firstName + " " + lastName + "</b> has been successfully fired.";
    }

    private int findFirstFreeIndex() throws NoFreeSlotsInArrayException {
        for (int index = 0; index < employees.length; index++) {
            if (employees[index] == null) {
                return index;
            }
        }
        throw new NoFreeSlotsInArrayException();
    }

    private void doesEmployeeExist(String firstName, String lastName) {
        if (getEmployeeIndex(firstName, lastName) >= 0) {
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
        for (int index = 0; index < employees.length; index++) {
            if (employee.equals(employees[index])) {
                return index;
            }
        }
        return -1;
    }
}
