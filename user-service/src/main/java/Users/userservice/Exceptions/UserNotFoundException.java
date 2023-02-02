package Users.userservice.Exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long userId) {
        super("User with id=" + userId + " was not found");
    }
}
