package com.bank_app.services.impl;

import com.bank_app.dto.AccountDto;
import com.bank_app.dto.UserDto;
import com.bank_app.models.User;
import com.bank_app.repositories.UserRepository;
import com.bank_app.services.AccountService;
import com.bank_app.services.UserService;
import com.bank_app.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final AccountService accountService;

    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer Save(UserDto dto) {

        validator.validate(dto);
        User user = UserDto.toEntity(dto);
       // User saveUser = repository.save(user);
       // return saveUser.getId();
        return repository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {

        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {

        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {

        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {

        User user  = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user Was found user account validation"));

        // create bank account
       AccountDto accountDto = AccountDto.builder()
               .user(UserDto.fromEntity(user))
               .build();
       accountService.Save(accountDto);

       //apres on active le compte
        user.setActive(true);
       repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {

        User user  = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user Was found user account validation"));

        user.setActive(false);
        repository.save(user);

        return user.getId();
    }
}
