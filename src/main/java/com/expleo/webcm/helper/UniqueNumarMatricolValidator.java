package com.expleo.webcm.helper;

import com.expleo.webcm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is returning if the registration number checked by annotation UniqueNumarMatricol is unique;
 * */

@Component
public class UniqueNumarMatricolValidator implements ConstraintValidator<UniqueNumarMatricol, Integer> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return !userService.foundNumarMatricolExpleo(value);
    }
}
