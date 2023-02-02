package Microservices.Departments.Exceptions;

public class DepartmentNotFoundException extends Exception{
    public DepartmentNotFoundException(Long departmentId) {
        super("Department with id=" + departmentId + " was not found");
    }
}
