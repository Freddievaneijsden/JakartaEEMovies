package com.example.rules;

import com.example.dto.CreateMovie;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidMovieValidator implements ConstraintValidator<ValidMovie, CreateMovie> {

    @Override
    public boolean isValid(CreateMovie createMovie, ConstraintValidatorContext constraintValidatorContext) {
        if (Character.isUpperCase(createMovie.title().charAt(0)) && createMovie.duration() > 0) {
            return true;
        }
        constraintValidatorContext
                .buildConstraintViolationWithTemplate("Title must contain uppercase first letter and duration must be greater then 0")
                .addPropertyNode("title").addConstraintViolation();
        return false;
    }
    }
