package com.example.mapper;

import com.example.dto.CreateMovie;
import com.example.entity.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MovieMapperTest {

    @Test
    @DisplayName("Map with no argument return null")
    void mapWithNoArgumentReturnNull() {
        CreateMovie newMovie = null;

        assertThat(MovieMapper.map(newMovie)).isEqualTo(null);
    }

    @Test
    @DisplayName("Map should return new Movie with same fields as CreateMovie")
    void mapShouldReturnNewMovieWithSameFieldsAsCreateMovie() {
        CreateMovie createMovie = new CreateMovie("Avengers", "Anthony Russo", 120, LocalDate.of(2012, 3, 10), "Action movie with marvel heroes");

        Movie expectedMovie = MovieMapper.map(createMovie);

        assertAll(
                () -> assertThat(createMovie.title()).isEqualTo(expectedMovie.getMovieTitle()),
                () -> assertThat(createMovie.duration()).isEqualTo(expectedMovie.getMovieDuration()),
                () -> assertThat(createMovie.releaseDate()).isEqualTo(expectedMovie.getMovieReleaseDate()),
                () -> assertThat(createMovie.description()).isEqualTo(expectedMovie.getMovieDescription()),
                () -> assertThat(createMovie.director()).isEqualTo(expectedMovie.getMovieDirector())
        );

    }
}