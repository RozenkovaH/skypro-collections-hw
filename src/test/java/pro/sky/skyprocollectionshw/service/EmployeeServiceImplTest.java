package pro.sky.skyprocollectionshw.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.exception.BadArgumentException;
import pro.sky.skyprocollectionshw.exception.EmployeeAlreadyExistsException;
import pro.sky.skyprocollectionshw.exception.EmployeeNotFoundException;
import pro.sky.skyprocollectionshw.service.impl.EmployeeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.skyprocollectionshw.constants.EmployeeServiceImplTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock private ValidationService validationService;

    @Mock private EmployeeFormatterService employeeFormatterService;

    @InjectMocks private EmployeeServiceImpl out;

    public static Stream<Arguments> provideParamsForAddEmployeeNegativeTest() {
        return Stream.of(
                Arguments.of(EMPLOYEE_WITH_EMPTY_NAME),
                Arguments.of(EMPLOYEE_WITH_NULL_NAME),
                Arguments.of(EMPLOYEE_WITH_NON_ALPHA_NAME)
        );
    }


    @Test
    public void getAllEmployees() {
        Map<String, Employee> employees = new LinkedHashMap<>(); // для сохранения порядка добавления значений
        employees.put(EMPLOYEE_1.getFirstName() + "|" + EMPLOYEE_1.getLastName(), EMPLOYEE_1);
        employees.put(EMPLOYEE_2.getFirstName() + "|" + EMPLOYEE_2.getLastName(), EMPLOYEE_2);
        employees.put(EMPLOYEE_3.getFirstName() + "|" + EMPLOYEE_3.getLastName(), EMPLOYEE_3);
        EmployeeService employeeService = new EmployeeServiceImpl(validationService, employeeFormatterService) {
            @Override
            public Map<String, Employee> getEmployees() {
                return employees;
            }
        };

        assertEquals(Arrays.asList(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3), employeeService.getAllEmployees());
    }

    @Test
    public void addEmployeePositive() {
        Mockito.when(employeeFormatterService.format(EMPLOYEE_1)).thenReturn(EMPLOYEE_1);
        assertEquals("Employee <b>" + EMPLOYEE_1.getFirstName() + " " + EMPLOYEE_1.getLastName() + "</b> has been successfully added.",
                out.addEmployee(EMPLOYEE_1));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForAddEmployeeNegativeTest")
    public void addEmployeeEmptyName(Employee employee) {
        Mockito.doThrow(new BadArgumentException()).when(validationService).validateEmployee(employee);

        assertThrows(BadArgumentException.class,
                () -> out.addEmployee(employee));
    }

    @Test
    public void addEmployeeExisting() {
        Mockito.when(employeeFormatterService.format(EMPLOYEE_1)).thenReturn(EMPLOYEE_1);

        out.addEmployee(EMPLOYEE_1);
        assertThrows(EmployeeAlreadyExistsException.class,
                () -> out.addEmployee(EMPLOYEE_1));
    }

    @Test
    public void findEmployeeExisting() {
        Mockito.when(employeeFormatterService.format(EMPLOYEE_1)).thenReturn(EMPLOYEE_1);

        out.addEmployee(EMPLOYEE_1);
        assertEquals(EMPLOYEE_1, out.findEmployee(EMPLOYEE_1));
    }

    @Test
    public void findEmployeeNonExisting() {
        Mockito.when(employeeFormatterService.format(EMPLOYEE_1)).thenReturn(EMPLOYEE_1);

        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee(EMPLOYEE_1));
    }


}
