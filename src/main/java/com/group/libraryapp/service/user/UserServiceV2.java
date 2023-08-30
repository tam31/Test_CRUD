package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.request.user.UserCreateRequest;
import com.group.libraryapp.dto.request.user.UserResponse;
import com.group.libraryapp.dto.request.user.UserUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(),user))
                .collect(Collectors.toList());
    }

    @Transactional
    public void userUpdate(UserUpdateRequest request){
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());
//        userRepository.save(user); 변경감지기능으로 영속성컨텐츠로 자동저장
    }

    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }
}
