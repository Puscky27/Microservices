package Users.userservice.Exceptions;

public class DepartmentNotFoundException extends Exception{
    public DepartmentNotFoundException(Long departmentId, Long userId) {
        super("Department with id=" + departmentId + " for user with id=" + userId + " was not found");
    }
}
