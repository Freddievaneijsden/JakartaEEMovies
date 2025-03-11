package com.example.dto;

import com.example.rules.ValidMovie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@ValidMovie
public record CreateMovie (
        @NotNull @NotBlank
        String title,
        String director,
        Integer duration,
        @Past
        LocalDate releaseDate,
        @Size (max = 500)
        String description)
{
}
