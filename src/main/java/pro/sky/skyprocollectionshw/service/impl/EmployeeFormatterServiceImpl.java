package pro.sky.skyprocollectionshw.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.service.EmployeeFormatterService;

@Service
public class EmployeeFormatterServiceImpl implements EmployeeFormatterService {

    @Override
    public Employee format(Employee employee) {
        return new Employee(toLowerAndCapitalize(employee.getFirstName()),
                toLowerAndCapitalize(employee.getLastName()),
                employee.getDepartment(),
                employee.getSalary());
    }

    private String toLowerAndCapitalize(String str) {
        return StringUtils.capitalize(str.toLowerCase());
    }
}