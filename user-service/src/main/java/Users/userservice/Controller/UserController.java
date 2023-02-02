package Users.userservice.Controller;

import Users.userservice.Exceptions.DepartmentNotFoundException;
import Users.userservice.Model.ResponseDto;
import Users.userservice.Model.User;
import Users.userservice.Exceptions.UserNotFoundException;
import Users.userservice.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User updatedUser) throws UserNotFoundException {
        User updateUser = userService.updateUser(userId, updatedUser);
        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List> getUsers(){
        List<User> userList = new ArrayList<>();
        userList=userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId) throws UserNotFoundException, DepartmentNotFoundException {
        ResponseDto responseDto = userService.getUser(userId);
        return ResponseEntity.ok(responseDto);
    }


}
