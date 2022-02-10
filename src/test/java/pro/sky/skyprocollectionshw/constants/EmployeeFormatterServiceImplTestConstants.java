package pro.sky.skyprocollectionshw.constants;

import pro.sky.skyprocollectionshw.data.Employee;

public class EmployeeFormatterServiceImplTestConstants {
    public static final Employee EMPLOYEE = new Employee("Иван", "Иванов", 1, 100000);
    public static final Employee EMPLOYEE_WITH_RANDOM_CASES = new Employee("иВаН", "иВаНОв", 1, 100000);
    public static final Employee EMPLOYEE_WITH_EMPTY_NAME = new Employee("", "", 1, 100000);
    public static final Employee EMPLOYEE_WITH_NULL_NAME = new Employee(null, null, 1, 100000);
}
