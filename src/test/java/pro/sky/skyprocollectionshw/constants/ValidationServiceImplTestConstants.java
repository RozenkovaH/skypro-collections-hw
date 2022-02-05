package pro.sky.skyprocollectionshw.constants;

import pro.sky.skyprocollectionshw.data.Employee;

public class ValidationServiceImplTestConstants {
    public static final Employee EMPLOYEE = new Employee("Иван", "Иванов", 1, 100000);
    public static final Employee EMPLOYEE_WITH_EMPTY_NAME = new Employee("", "", 1, 100000);
    public static final Employee EMPLOYEE_WITH_NULL_NAME = new Employee(null, null, 1, 100000);
    public static final Employee EMPLOYEE_WITH_NON_ALPHA_NAME = new Employee("Ру$лан", "Русл@нов", 1, 100000);
    public static final Employee EMPLOYEE_WITH_NON_EXISTING_DEPARTMENT = new Employee("Иван", "Иванов", 7, 100000);
}
