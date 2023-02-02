package Microservices.Departments.Service;

import Microservices.Departments.Exceptions.DepartmentNotFoundException;
import Microservices.Departments.Model.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    List<Department> findAll();
}
