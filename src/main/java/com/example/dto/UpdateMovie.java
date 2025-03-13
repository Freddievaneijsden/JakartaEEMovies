package com.example.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateMovie (
        @NotBlank
        String title,
        @Positive
        Integer duration,
        String director,
        @Past
        LocalDate releaseDate,
        @Size (max = 500)
        String description) {
}
