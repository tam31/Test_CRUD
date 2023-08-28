package com.group.libraryapp.controller.calculator.user;

import com.group.libraryapp.dto.request.user.UserCreateRequest;
import com.group.libraryapp.dto.request.user.UserResponse;
import com.group.libraryapp.dto.request.user.UserUpdateRequest;

import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserServiceV2 userService;

    public UserController(UserServiceV2 userService) {
//        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userService.userUpdate(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userService.deleteUser(name);
    }

}
