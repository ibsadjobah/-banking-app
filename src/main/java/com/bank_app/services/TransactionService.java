package com.bank_app.services;

import com.bank_app.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto> {

    List<TransactionDto> findAllByUserId(Integer userId);
}
