package com.group.libraryapp.controller.calculator.user;

import com.group.libraryapp.dto.request.user.UserCreateRequest;
import com.group.libraryapp.dto.request.user.UserResponse;
import com.group.libraryapp.dto.request.user.UserUpdateRequest;
import com.group.libraryapp.service.user.UserServiceV1;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserServiceV1 userServiceV1;

    public UserController(UserServiceV1 userServiceV1) {
//        this.jdbcTemplate = jdbcTemplate;
        this.userServiceV1 = userServiceV1;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        userServiceV1.saveUser(request.getName(), request.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        return userServiceV1.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userServiceV1.userUpdate(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userServiceV1.deleteUser(name);
    }

}
