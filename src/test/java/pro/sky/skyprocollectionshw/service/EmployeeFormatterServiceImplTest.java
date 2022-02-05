package pro.sky.skyprocollectionshw.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.service.impl.EmployeeFormatterServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pro.sky.skyprocollectionshw.constants.EmployeeFormatterServiceImplTestConstants.*;

public class EmployeeFormatterServiceImplTest {

    private final EmployeeFormatterServiceImpl out = new EmployeeFormatterServiceImpl();

    public static Stream<Arguments> provideParamsForFormatTest() {
        return Stream.of(
                Arguments.of(EMPLOYEE, EMPLOYEE),
                Arguments.of(EMPLOYEE, EMPLOYEE_WITH_RANDOM_CASES),
                Arguments.of(EMPLOYEE_WITH_EMPTY_NAME, EMPLOYEE_WITH_EMPTY_NAME),
                Arguments.of(EMPLOYEE_WITH_NULL_NAME, EMPLOYEE_WITH_NULL_NAME)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForFormatTest")
    public void formatFormatted(Employee formattedEmployee, Employee employeeToFormat) {
        assertEquals(formattedEmployee, out.format(employeeToFormat));
    }
}
