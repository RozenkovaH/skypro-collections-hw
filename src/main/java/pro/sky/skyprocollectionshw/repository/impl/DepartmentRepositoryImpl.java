package pro.sky.skyprocollectionshw.repository.impl;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import pro.sky.skyprocollectionshw.repository.DepartmentRepository;

@Component
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final List<Integer> departments = Arrays.asList(1, 2, 3, 4, 5);

    @Override
    public List<Integer> getAllDepartments() {
        return departments;
    }

    @Override
    public boolean doesDepartmentExist(int department) {
        return getAllDepartments().stream().anyMatch(d -> d.equals(department));
    }
}
