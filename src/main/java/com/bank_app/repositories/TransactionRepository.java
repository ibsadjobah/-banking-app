package com.bank_app.repositories;

import com.bank_app.dto.TransactionDto;
import com.bank_app.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserId(Integer userId);
}
