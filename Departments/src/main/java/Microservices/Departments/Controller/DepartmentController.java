package Microservices.Departments.Controller;

import Microservices.Departments.Exceptions.DepartmentNotFoundException;
import Microservices.Departments.Model.Department;
import Microservices.Departments.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List> getDepartments(){
        List<Department> departmentList=new ArrayList<>();
        departmentList=departmentService.findAll();
        return ResponseEntity.ok(departmentList);
    }
    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        Department department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }
}
