package com.bank_app.controllers;

import com.bank_app.dto.UserDto;
import com.bank_app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody UserDto userDto){

        return ResponseEntity.ok(userService.Save(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(userService.findById(userId));
    }

    @PatchMapping("/validate{userId}")
    public ResponseEntity<Integer> validateAccount(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(userService.validateAccount(userId));
    }

    @PatchMapping("/invalidate{userId}")
    public ResponseEntity<Integer> inValidateAccount(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(userService.invalidateAccount(userId));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Integer userId){

        userService.delete(userId);
        return ResponseEntity.accepted().build();

    }

}
