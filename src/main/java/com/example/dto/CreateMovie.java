package com.example.dto;

import com.example.rules.ValidMovie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@ValidMovie
public record CreateMovie (
        @NotNull @NotBlank String title,
        String director,
        Integer duration,
        LocalDate releaseDate,
        String description)

{
}
