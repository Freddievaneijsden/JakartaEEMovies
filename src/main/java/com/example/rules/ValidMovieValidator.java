package com.example.rules;

import com.example.business.MovieService;
import com.example.dto.CreateMovie;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.ws.rs.NotFoundException;

public class ValidMovieValidator implements ConstraintValidator<ValidMovie, CreateMovie> {

    private MovieService movieService;

    @Inject
    public ValidMovieValidator(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public boolean isValid(CreateMovie createMovie, ConstraintValidatorContext constraintValidatorContext) {
        if (movieService.isValidTitle(createMovie.title())) {
            return true;
        }
        constraintValidatorContext
                .buildConstraintViolationWithTemplate("Movie with title " + createMovie.title() + " already exist")
                .addPropertyNode("title").addConstraintViolation();
        return false;
    }
    }
