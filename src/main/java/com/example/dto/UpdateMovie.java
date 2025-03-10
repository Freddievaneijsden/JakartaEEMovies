package com.example.dto;

import java.time.LocalDate;

public record UpdateMovie (String title, Integer duration, String director, LocalDate releaseDate, String description) {
}
