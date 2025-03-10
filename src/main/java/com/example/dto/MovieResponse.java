package com.example.dto;

import com.example.entity.Movie;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.entity.Movie}
 */
public record MovieResponse(Long id, @NotBlank(message = "Movie title required") String title,
                            Integer duration, String director, LocalDate releaseDate, String description) implements Serializable {

    public MovieResponse(Movie movie) {
        this(movie.getMovieId(), movie.getMovieTitle(), movie.getMovieDuration(), movie.getMovieDirector(), movie.getMovieReleaseDate(), movie.getMovieDescription());
    }

    public static MovieResponse map(Movie movie) {
        return new MovieResponse(movie.getMovieId(), movie.getMovieTitle(), movie.getMovieDuration(), movie.getMovieDirector(), movie.getMovieReleaseDate(), movie.getMovieDescription());
    }
}