package com.bank_app.controllers;

import com.bank_app.dto.AccountDto;
import com.bank_app.services.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
@Tag(name = "accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody AccountDto accountDto) {

        return ResponseEntity.ok(accountService.Save(accountDto));

    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> findAll(){

        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("{accountId}")
    public ResponseEntity<AccountDto> findById(@PathVariable("accountId") Integer accountId){

        return ResponseEntity.ok(accountService.findById(accountId));
    }

    @DeleteMapping("{accountId}")
    public ResponseEntity<Void> delete(@PathVariable("accountId") Integer accountId){

        accountService.delete(accountId);
        return ResponseEntity.accepted().build();
    }

}
