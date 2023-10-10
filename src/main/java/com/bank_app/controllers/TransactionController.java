package com.bank_app.controllers;

import com.bank_app.dto.TransactionDto;
import com.bank_app.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody TransactionDto transactionDto) {

        return ResponseEntity.ok(transactionService.Save(transactionDto));

    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> findAll(){

        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("{transactionId}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("transactionId") Integer transactionId){

        return ResponseEntity.ok(transactionService.findById(transactionId));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<TransactionDto>> findAllByUserId(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(transactionService.findAllByUserId(userId));
    }

    @DeleteMapping("{transactionId}")
    public ResponseEntity<Void> delete(@PathVariable("transactionId") Integer transactionId){

        transactionService.delete(transactionId);
        return ResponseEntity.accepted().build();
    }
}
