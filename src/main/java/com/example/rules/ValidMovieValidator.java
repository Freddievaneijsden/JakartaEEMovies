package com.example.rules;

import com.example.dto.CreateMovie;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidMovieValidator implements ConstraintValidator<ValidMovie, CreateMovie> {

    @Override
    public boolean isValid(CreateMovie createMovie, ConstraintValidatorContext constraintValidatorContext) {
//        if (Character.isUpperCase(createMovie.title().charAt(0)))
        return false;

//        return switch (value) {
//            case CreateMovie movie -> !movie.title().isEmpty();
//            default -> false;
//        }; Bättre att implementera flera olika Validators än att skapa en switch som hanterar olika datatyper

    }
}
