package com.group.libraryapp.controller.calculator.user;

import com.group.libraryapp.dto.request.user.UserCreateRequest;
import com.group.libraryapp.dto.request.user.UserResponse;
import com.group.libraryapp.dto.request.user.UserUpdateRequest;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
//        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request.getName(), request.getAge());
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
