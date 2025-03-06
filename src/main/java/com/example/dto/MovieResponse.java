package com.example.dto;

import com.example.entity.Movie;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.entity.Movie}
 */
public record MovieResponse(Long movieId, @NotBlank(message = "Movie title required") String movieTitle,
                            BigDecimal moviePrice, String movieGenre) implements Serializable {

    public MovieResponse(Movie movie) {
        this(movie.getMovieId(), movie.getMovieTitle(), movie.getMoviePrice(), movie.getMovieGenre());
    }

    public static MovieResponse map(Movie movie) {
        return new MovieResponse(movie.getMovieId(), movie.getMovieTitle(), movie.getMoviePrice(), movie.getMovieGenre());
    }
}