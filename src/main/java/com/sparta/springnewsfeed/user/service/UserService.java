package com.sparta.springnewsfeed.user.service;

import com.sparta.springnewsfeed.user.dto.LoginRequestDto;
import com.sparta.springnewsfeed.user.dto.UserRequestDto;
import com.sparta.springnewsfeed.user.dto.UserResponseDto;
import com.sparta.springnewsfeed.user.entity.User;
import com.sparta.springnewsfeed.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class UserService {

    private final UserRepository userRepository;
    public UserResponseDto signup(@Valid UserRequestDto userRequest) {
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

    public UserResponseDto login(LoginRequestDto logInRequest) {
        String email = logInRequest.getEmail();
        String password = logInRequest.getPassword();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong Password");
        }
        UserResponseDto userResponse = new UserResponseDto(user);
        return userResponse;
    }


    public String delete(Long id, LoginRequestDto logInRequest) {
        String email = logInRequest.getEmail();
        String password = logInRequest.getPassword();

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(!user.getPassword().equals(password) || !user.getEmail().equals(email)) {
            throw new IllegalArgumentException("유저 정보가 일치 하지 않습니다.");
        }
        userRepository.deleteById(id);
        return "User Deleted";
    }
}
