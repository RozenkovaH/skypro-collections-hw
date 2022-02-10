package pro.sky.skyprocollectionshw.repository;

import java.util.List;

public interface DepartmentRepository {

    List<Integer> getAllDepartments();
    boolean doesDepartmentExist(int department);
}
