package pro.sky.skyprocollectionshw.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.exception.BadArgumentException;
import pro.sky.skyprocollectionshw.repository.DepartmentRepository;
import pro.sky.skyprocollectionshw.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

    private final DepartmentRepository departmentRepository;

    public ValidationServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void validateEmployee(Employee employee) {
        if (!StringUtils.isAlpha(employee.getFirstName()) || !StringUtils.isAlpha(employee.getLastName())) {
            throw new BadArgumentException("String is empty or contains non-alpha characters.");
        }
        validateDepartment(employee.getDepartment());
    }

    @Override
    public void validateDepartment(int department) {
        if (!departmentRepository.doesDepartmentExist(department)) {
            throw new BadArgumentException("Department does not exist");
        }
    }
}
