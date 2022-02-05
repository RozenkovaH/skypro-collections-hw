package pro.sky.skyprocollectionshw.service;

import pro.sky.skyprocollectionshw.data.Employee;

public interface ValidationService {

    void validateEmployee(Employee employee);
    void validateDepartment(int department);
}
