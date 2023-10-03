package com.bank_app.dto;

import com.bank_app.models.Contact;
import com.bank_app.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ContactDto {

    private String firstname;

    private String lastname;

    private String email;

    private String iban;

    private Integer userId;

    public static ContactDto fromEntity(Contact contact){

        return ContactDto.builder()
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();
    }

    public static Contact toEntity(ContactDto contact){

        return Contact.builder()
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user(
                        User.builder()
                                .id(contact.getUserId())
                                .build()
                )
                .build();
    }



}
