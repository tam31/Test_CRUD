package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.request.user.UserResponse;
import com.group.libraryapp.dto.request.user.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {

    private final UserJdbcRepository repository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.repository = userJdbcRepository;
    }

    public void saveUser(String name, int age){
        repository.saveUser(name,age);
    }

    public List<UserResponse> getUsers(){
        return repository.getUserResponses();
    }
    public void userUpdate(UserUpdateRequest request){
        boolean isUserNotExist = repository.isUserNotExist(request.getId());
        if(isUserNotExist){
            throw new IllegalArgumentException();
        }

        repository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name){
        if(repository.isUserNotExist(name)){
            throw new IllegalArgumentException();
        }

        repository.deleteUserByName(name);
    }
}
