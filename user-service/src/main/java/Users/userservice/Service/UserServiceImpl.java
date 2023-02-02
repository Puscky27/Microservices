package Users.userservice.Service;

import Users.userservice.Exceptions.DepartmentNotFoundException;
import Users.userservice.Exceptions.UserNotFoundException;
import Users.userservice.Model.DepartmentDto;
import Users.userservice.Model.ResponseDto;
import Users.userservice.Model.User;
import Users.userservice.Model.UserDto;
import Users.userservice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User updatedUser) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Optional.ofNullable(updatedUser.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(updatedUser.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(updatedUser.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(updatedUser.getDepartmentId()).ifPresent(user::setDepartmentId);
        return userRepository.save(user);
    }
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Override
    public ResponseDto getUser(Long userId) throws UserNotFoundException, DepartmentNotFoundException {

        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        UserDto userDto = mapToUser(user);

        try {
            ResponseEntity<DepartmentDto> responseEntity = restTemplate
                    .getForEntity("http://localhost:8080/api/departments/{departmentId}",
                            DepartmentDto.class,
                            user.getDepartmentId());
            DepartmentDto departmentDto = responseEntity.getBody();

            System.out.println(responseEntity.getStatusCode());

            responseDto.setUser(userDto);
            responseDto.setDepartment(departmentDto);
            return responseDto;
        }
        catch(Exception e) {
            throw new DepartmentNotFoundException(Long.parseLong(user.getDepartmentId()),userId);
        }
    }

    private UserDto mapToUser(User user){
        UserDto userDto= new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}


