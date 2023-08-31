package com.bank_app.models;

import lombok.Builder;
import lombok.Data;


import javax.persistence.*;

@Data
@Builder
@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String iban;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


}
