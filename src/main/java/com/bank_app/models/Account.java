package com.bank_app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractEntity{

    private String iban;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;



}
