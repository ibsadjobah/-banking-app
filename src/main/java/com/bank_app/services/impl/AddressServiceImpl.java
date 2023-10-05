package com.bank_app.services.impl;

import com.bank_app.dto.AddressDto;
import com.bank_app.models.Address;
import com.bank_app.repositories.AddressRepository;
import com.bank_app.services.AddressService;
import com.bank_app.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    private final ObjectsValidator<AddressDto> validator;
    @Override
    public Integer Save(AddressDto dto) {

        validator.validate(dto);

        Address address = AddressDto.toEntity(dto);

        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll().stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No address was found with the ID : " +id));
    }

    @Override
    public void delete(Integer id) {

        repository.deleteById(id);

    }
}
