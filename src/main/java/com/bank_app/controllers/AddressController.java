package com.bank_app.controllers;

import com.bank_app.dto.AddressDto;
import com.bank_app.services.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/addresses")
@RestController
@RequiredArgsConstructor
@Tag(name = "address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody AddressDto addressDto){

        return ResponseEntity.ok(addressService.Save(addressDto));
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll(){

        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("{addressId}")
    public ResponseEntity<AddressDto> findById(@PathVariable("addressId") Integer addressId){

        return ResponseEntity.ok(addressService.findById(addressId));
    }

    @DeleteMapping("{addressId}")
    public ResponseEntity<Void> delete(@PathVariable("addressId") Integer addressId){

            addressService.delete(addressId);
            return ResponseEntity.accepted().build();
    }


}
