package pro.sky.skyprocollectionshw.constants;

import pro.sky.skyprocollectionshw.data.Employee;

public class EmployeeServiceImplTestConstants {
    public static final Employee EMPLOYEE_1 = new Employee("Иван", "Иванов", 1, 100000);
    public static final Employee EMPLOYEE_2 = new Employee("Петр", "Петров", 2, 200000);
    public static final Employee EMPLOYEE_3 = new Employee("Семен", "Семенов", 3, 300000);
    public static final Employee EMPLOYEE_WITH_EMPTY_NAME = new Employee("", "", 1, 120000);
    public static final Employee EMPLOYEE_WITH_NULL_NAME = new Employee(null, null, 1, 120000);
    public static final Employee EMPLOYEE_WITH_NON_ALPHA_NAME = new Employee("$ева", "3уев", 1, 120000);
}
