package pro.sky.skyprocollectionshw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import pro.sky.skyprocollectionshw.data.Employee;
import pro.sky.skyprocollectionshw.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public Collection<Employee> getAllEmployeesByDept(@RequestParam(name = "departmentId", required = false) Integer department) {
        if (department == null) {
            return departmentService.getAllEmployees();
        }
        return departmentService.getAllEmployeesByDept(department);
    }

    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalaryInDept(@RequestParam("departmentId") int department) {
        return departmentService.findEmployeeWithMaxSalaryInDept(department);
    }

    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalaryInDept(@RequestParam("departmentId") int department) {
        return departmentService.findEmployeeWithMinSalaryInDept(department);
    }
}
