package com.example.dto;

import com.example.entity.Movie;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.example.entity.Movie}
 */
public record MovieResponse(Long movieId, @NotBlank(message = "Movie title required") String movieTitle,
                            Integer movieDuration, String movieDirector) implements Serializable {

    public MovieResponse(Movie movie) {
        this(movie.getMovieId(), movie.getMovieTitle(), movie.getMovieDuration(), movie.getMovieDirector());
    }

    public static MovieResponse map(Movie movie) {
        return new MovieResponse(movie.getMovieId(), movie.getMovieTitle(), movie.getMovieDuration(), movie.getMovieDirector());
    }
}