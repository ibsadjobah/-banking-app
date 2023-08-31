package com.bank_app.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

}
