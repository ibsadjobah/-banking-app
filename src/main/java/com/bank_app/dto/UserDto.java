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

    @NotNull(message = "le Prenom ne doit pas être vide ")
    @NotEmpty(message = "le Prenom ne doit pas être vide ")
    @NotBlank(message = "le Prenom ne doit pas être vide ")
    private String firstname;

    @NotNull(message = "le Nom ne doit pas être vide ")
    @NotEmpty(message = "le Nom ne doit pas être vide ")
    @NotBlank(message = "le Nom ne doit pas être vide ")
    private String lastname;

    @NotNull(message = "l'email ne doit pas être vide ")
    @NotEmpty(message = "l'email ne doit pas être vide ")
    @NotBlank(message = "l'email ne doit pas être vide ")
    @Email(message = "l'email ne doit pas être vide ")
    private String email;

    @NotNull(message = "le mot de passe ne doit pas être vide ")
    @NotEmpty(message = "le mot de passe ne doit pas être vide ")
    @NotBlank(message = "le mot de passe ne doit pas être vide ")
    @Size(min = 8 , max = 16, message = "le mot de passe doit être compris entre 8 et 16 caractères")
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
