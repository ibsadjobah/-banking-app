package com.bank_app.dto;

import com.bank_app.models.User;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserDto {

    private Integer id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String firstname;

    @NotNull
    @NotEmpty
    @NotBlank
    private String lastname;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8 , max = 16)
    private String password;

    private LocalDateTime birthDate;


    public static UserDto fromEntity(User user){

        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto user){

        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
