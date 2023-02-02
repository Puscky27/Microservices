package Microservices.Departments.Service;

import Microservices.Departments.Exceptions.DepartmentNotFoundException;
import Microservices.Departments.Model.Department;
import Microservices.Departments.Repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException(departmentId));
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
