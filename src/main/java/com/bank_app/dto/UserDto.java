package com.bank_app.dto;

import com.bank_app.models.User;
import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;


    public static UserDto fromEntity(User user){

        return UserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto user){

        return User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
