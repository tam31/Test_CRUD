package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.domain.UserRepository;
import com.group.libraryapp.dto.request.user.UserCreateRequest;
import com.group.libraryapp.dto.request.user.UserResponse;
import com.group.libraryapp.dto.request.user.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(),user))
                .collect(Collectors.toList());
    }

    public void userUpdate(UserUpdateRequest request){
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());
        userRepository.save(user);
    }

    public void deleteUser(String name){
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }
}
