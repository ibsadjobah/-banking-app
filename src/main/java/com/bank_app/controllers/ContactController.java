package com.bank_app.controllers;

import com.bank_app.dto.AccountDto;
import com.bank_app.dto.ContactDto;
import com.bank_app.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/contacts")
public class ContactController {

    private final ContactService contactService;


    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody ContactDto contactDto) {

        return ResponseEntity.ok(contactService.Save(contactDto));

    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> findAll(){

        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("{contactId}")
    public ResponseEntity<ContactDto> findById(@PathVariable("contactId") Integer contactId){

        return ResponseEntity.ok(contactService.findById(contactId));
    }

    @GetMapping("/api/v1/users/{userId}")
    public ResponseEntity<List<ContactDto>> findAllByUserId(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(contactService.findAllByUserId(userId));
    }

    @DeleteMapping("{contactId}")
    public ResponseEntity<Void> delete(@PathVariable("contactId") Integer contactId){

        contactService.delete(contactId);
        return ResponseEntity.accepted().build();
    }
}
