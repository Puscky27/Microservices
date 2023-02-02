package Users.userservice.Service;

import Users.userservice.Exceptions.DepartmentNotFoundException;
import Users.userservice.Exceptions.UserNotFoundException;
import Users.userservice.Model.ResponseDto;
import Users.userservice.Model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    ResponseDto getUser(Long userID) throws UserNotFoundException, DepartmentNotFoundException;

    List<User> findAll();

    User updateUser(Long userId, User updatedUser) throws UserNotFoundException;
}
