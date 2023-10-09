package com.bank_app.services.impl;

import com.bank_app.dto.AccountDto;
import com.bank_app.exceptions.OperationNonPermittedException;
import com.bank_app.models.Account;
import com.bank_app.repositories.AccountRepository;
import com.bank_app.services.AccountService;
import com.bank_app.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.xml.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer Save(AccountDto dto) {

        //BLOCK ACCOUNT UPDATED -> IBAN CANNOT BE CHANGED
       /* if(dto.getId() != null)
        {
            throw new OperationNonPermittedException(
                    "Account cannot be updated",
                    "Save Account",
                    "Account",
                    "Updated not permitted"
            );
        }*/

        validator.validate(dto);

        Account account = AccountDto.toEntity(dto);

        boolean UserHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        if (UserHasAlreadyAnAccount) {
            throw new OperationNonPermittedException(
                    "The selected User has already an active Account",
                    "Create Account ",
                    "Account Service ",
                    "Account creation"

            );
        }
        // generate random IBAN when creating new account else do not updated the IBAN
        if(dto.getId() == null){

            account.setIban(generateRandomIban());
        }

        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll().stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No acount was found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {

        repository.deleteById(id);

    }

    private String generateRandomIban(){

        //first generate an iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        //check if the iban exist
       boolean ibanExists =  repository.findByIban(iban).isPresent();

       if (ibanExists)
       {
           generateRandomIban();
       }

       return iban;
    }
}
