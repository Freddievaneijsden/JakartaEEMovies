package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateMovie (
        @NotNull @NotBlank String title,
        String director,
        @Positive Integer duration,
        LocalDate releaseDate,
        String description)

{
}
