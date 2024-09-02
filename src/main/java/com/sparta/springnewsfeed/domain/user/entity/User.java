package com.sparta.springnewsfeed.domain.user.entity;

import com.sparta.springnewsfeed.domain.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name ="users")
@NoArgsConstructor
public class User extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long id;
    private String username;
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public User(UserRequestDto userRequest){
        this.username = userRequest.getUsername();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
    }
}
