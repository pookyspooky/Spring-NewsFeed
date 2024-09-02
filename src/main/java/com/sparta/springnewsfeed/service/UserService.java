package com.sparta.springnewsfeed.service;

import com.sparta.springnewsfeed.dto.UserRequestDto;
import com.sparta.springnewsfeed.dto.UserResponseDto;
import com.sparta.springnewsfeed.entity.User;
import com.sparta.springnewsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserResponseDto signup(UserRequestDto userRequest) {
        String userName = userRequest.getUsername();
        String password = userRequest.getPassword();
        String email = userRequest.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }
        // RequestDto -> Entity
        User user = new User(userRequest);
        //DB 저장
        User saveUser = userRepository.save(user);
        UserResponseDto userResponse = new UserResponseDto(saveUser);
        return userResponse;
    }
}
