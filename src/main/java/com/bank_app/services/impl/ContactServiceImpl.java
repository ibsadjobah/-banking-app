package com.bank_app.services.impl;

import com.bank_app.dto.ContactDto;
import com.bank_app.models.Contact;
import com.bank_app.repositories.ContactRepository;
import com.bank_app.services.ContactService;
import com.bank_app.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;

    private final ObjectsValidator<ContactDto> validator;
    @Override
    public Integer Save(ContactDto dto) {

        validator.validate(dto);

        Contact contact = ContactDto.toEntity(dto);

        return repository.save(contact).getId();

    }

    @Override
    public List<ContactDto> findAll() {

        return repository.findAll().stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(() ->  new EntityNotFoundException("No contact was found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {

        repository.deleteById(id);

    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }
}
