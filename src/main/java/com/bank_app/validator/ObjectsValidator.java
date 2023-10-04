package com.bank_app.validator;

import com.bank_app.exceptions.ObjectValidationException;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T>{

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final Validator validator = factory.getValidator();

    public void validate(T objectToValidate){

       Set<ConstraintViolation<T>> violations =    validator.validate(objectToValidate);
        if (!violations.isEmpty()){
            Set<String> errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

            throw new ObjectValidationException(errorMessage, objectToValidate.getClass().getName());

            //todo raise an exception
        }
    }
}
