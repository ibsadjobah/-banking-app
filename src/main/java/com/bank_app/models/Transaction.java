package com.bank_app.models;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Integer id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String destinationIban;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
