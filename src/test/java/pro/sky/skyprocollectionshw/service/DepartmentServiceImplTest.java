package pro.sky.skyprocollectionshw.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import pro.sky.skyprocollectionshw.exception.BadArgumentException;
import pro.sky.skyprocollectionshw.service.impl.DepartmentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.skyprocollectionshw.constants.DepartmentServiceImplTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock private EmployeeService employeeService;

    @Mock private ValidationService validationService;

    @InjectMocks private DepartmentServiceImpl out;

    @Test
    public void getAllEmployees() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5));

        assertEquals(Arrays.asList(EMPLOYEE_2, EMPLOYEE_4, EMPLOYEE_3, EMPLOYEE_5, EMPLOYEE_1),
                out.getAllEmployees());
    }

    @Test
    public void getAllEmployeesByDeptExisting() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5, EMPLOYEE_6));

        assertEquals(Arrays.asList(EMPLOYEE_5, EMPLOYEE_6), out.getAllEmployeesByDept(4));
    }

    @Test
    public void getAllEmployeesByDeptExistingEmpty() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(EMPLOYEE_1, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5, EMPLOYEE_6));

        assertEquals(Collections.emptyList(), out.getAllEmployeesByDept(1));
    }

    @Test
    public void getAllEmployeesByDeptNonExisting() {
        Mockito.doThrow(new BadArgumentException()).when(validationService).validateDepartment(6);

        assertThrows(BadArgumentException.class,
                () -> out.getAllEmployeesByDept(6));
    }

    @Test
    public void findEmployeeWithMaxSalaryInDept() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5, EMPLOYEE_6));

        assertEquals(EMPLOYEE_5, out.findEmployeeWithMaxSalaryInDept(4));
    }

    @Test
    public void findEmployeeWithMaxSalaryInEmptyDept() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4));

        assertNull(out.findEmployeeWithMaxSalaryInDept(4));
    }

    @Test
    public void findEmployeeWithMaxSalaryInNonExistingDept() {
        Mockito.doThrow(new BadArgumentException()).when(validationService).validateDepartment(6);

        assertThrows(BadArgumentException.class,
                () -> out.findEmployeeWithMaxSalaryInDept(6));
    }

    @Test
    public void findEmployeeWithMinSalaryInDept() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5, EMPLOYEE_6));

        assertEquals(EMPLOYEE_6, out.findEmployeeWithMinSalaryInDept(4));
    }

    @Test
    public void findEmployeeWithMinSalaryInEmptyDept() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4));

        assertNull(out.findEmployeeWithMinSalaryInDept(4));
    }

    @Test
    public void findEmployeeWithMinSalaryInNonExistingDept() {
        Mockito.doThrow(new BadArgumentException()).when(validationService).validateDepartment(6);

        assertThrows(BadArgumentException.class,
                () -> out.findEmployeeWithMinSalaryInDept(6));
    }
}
