package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateMovie (
        @NotNull @NotBlank String title,
        String genre,
        @Positive BigDecimal price){
}
