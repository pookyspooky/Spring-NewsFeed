package com.sparta.springnewsfeed.domain.user.service;

import com.sparta.springnewsfeed.global.config.PasswordEncoder;
import com.sparta.springnewsfeed.domain.user.dto.*;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import com.sparta.springnewsfeed.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public UserResponseDto signup(@Valid UserRequestDto userRequest, HttpServletResponse res) {
        String userName = userRequest.getUsername();
        String password = passwordEncoder.encode(userRequest.getPassword());

        String email = userRequest.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }
        // RequestDto -> Entity
        User user = new User(userRequest,password);
        //DB 저장
        User saveUser = userRepository.save(user);
        //token생성 및 쿠키
        String token = jwtUtil.createToken(saveUser.getId());
        jwtUtil.addJwtToCookie(token,res);
        UserResponseDto userResponse = new UserResponseDto(saveUser);
        return userResponse;
    }

    public LoginResponseDto login(LoginRequestDto logInRequest, HttpServletResponse res) {
        String email = logInRequest.getEmail();
        String password = logInRequest.getPassword();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Wrong Password");
        }
        String token = jwtUtil.createToken(user.getId());
        jwtUtil.addJwtToCookie(token, res);
        return new LoginResponseDto(token);
    }


    public String delete(Long id, LoginRequestDto logInRequest) {
        String email = logInRequest.getEmail();
        String password = logInRequest.getPassword();

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(!passwordEncoder.matches(password, user.getPassword()) || !user.getEmail().equals(email)) {
            throw new IllegalArgumentException("유저 정보가 일치 하지 않습니다.");
        }
        userRepository.deleteById(id);
        return "User Deleted";
    }

    public String changePassword(Long id, ChangePasswordRequestDto passwordRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(!passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("현제 비밀번호가 일치하지않습니다.");
        }
        String password = passwordEncoder.encode(passwordRequest.getNewPassword());
        user.changePassword(password);
        userRepository.save(user);
        return "Password Changed";
    }
}
