package com.example.dto;

import com.example.rules.ValidMovie;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@ValidMovie
public record CreateMovie (
        @NotNull @NotBlank
        String title,
        String director,
        @Positive
        Integer duration,
        @Past
        LocalDate releaseDate,
        @Size (max = 500)
        String description)
{
}
