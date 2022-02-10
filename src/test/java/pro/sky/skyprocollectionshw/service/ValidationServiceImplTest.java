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

import java.util.stream.Stream;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.exception.BadArgumentException;
import pro.sky.skyprocollectionshw.repository.DepartmentRepository;
import pro.sky.skyprocollectionshw.service.impl.ValidationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.skyprocollectionshw.constants.ValidationServiceImplTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private ValidationServiceImpl out;

    public static Stream<Arguments> provideParamsForValidateEmployeeTest() {
        return Stream.of(
                Arguments.of(EMPLOYEE_WITH_EMPTY_NAME),
                Arguments.of(EMPLOYEE_WITH_NULL_NAME),
                Arguments.of(EMPLOYEE_WITH_NON_ALPHA_NAME)
        );
    }

    @Test
    public void validateEmployeePositive() {
        Mockito.when(departmentRepository.doesDepartmentExist(EMPLOYEE.getDepartment())).thenReturn(true);

        assertDoesNotThrow(() -> out.validateEmployee(EMPLOYEE));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForValidateEmployeeTest")
    public void validateEmployeeThrowsError(Employee employee) {
        assertThrows(BadArgumentException.class, () -> out.validateEmployee(employee));
    }

    @Test
    public void validateEmployeeNonExistingDepartment() {
        Mockito.when(departmentRepository.doesDepartmentExist(EMPLOYEE_WITH_NON_EXISTING_DEPARTMENT.getDepartment())).thenReturn(false);

        assertThrows(BadArgumentException.class, () -> out.validateEmployee(EMPLOYEE_WITH_NON_EXISTING_DEPARTMENT));
    }
}
