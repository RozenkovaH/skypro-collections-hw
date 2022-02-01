package pro.sky.skyprocollectionshw.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.exception.BadArgumentException;
import pro.sky.skyprocollectionshw.service.EmployeeValidationService;

@Service
public class EmployeeValidationServiceImpl implements EmployeeValidationService {

    @Override
    public void validate(Employee employee) {
        if (!StringUtils.isAlpha(employee.getFirstName()) || !StringUtils.isAlpha(employee.getLastName())) {
            throw new BadArgumentException();
        }
    }
}
