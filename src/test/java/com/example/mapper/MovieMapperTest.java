package com.example.mapper;

import com.example.dto.CreateMovie;
import com.example.dto.MovieResponse;
import com.example.entity.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MovieMapperTest {

    @Test
    @DisplayName("Map to Movie with no argument return null")
    void mapToMovieWithNoArgumentReturnNull() {
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

    @Test
    @DisplayName("Map to MovieResponse with no argument return null")
    void mapToMovieResponseWithNoArgumentReturnNull() {
        Movie movie = null;

        assertThat(MovieMapper.map(movie)).isEqualTo(null);
    }

    @Test
    @DisplayName("Map should return MovieRespons with same fields as Movie")
    void mapShouldReturnMovieResponsWithSameFieldsAsMovie() {
        Movie movie = new Movie();
        movie.setMovieId(1L);
        movie.setMovieTitle("The Dark Knight");
        movie.setMovieDescription("Action movie that takes place in Gotham ");
        movie.setMovieReleaseDate(LocalDate.of(2010, 3, 10));
        movie.setMovieDirector("Christopher Nolan");
        movie.setMovieDuration(120);

        MovieResponse expectedMovieRespons = MovieMapper.map(movie);

        assertAll(
                () -> assertThat(expectedMovieRespons).isInstanceOf(MovieResponse.class),
                () -> assertThat(expectedMovieRespons.id()).isEqualTo(movie.getMovieId()),
                () -> assertThat(expectedMovieRespons.title()).isEqualTo(movie.getMovieTitle()),
                () -> assertThat(expectedMovieRespons.director()).isEqualTo(movie.getMovieDirector()),
                () -> assertThat(expectedMovieRespons.duration()).isEqualTo(movie.getMovieDuration()),
                () -> assertThat(expectedMovieRespons.description()).isEqualTo(movie.getMovieDescription()),
                () -> assertThat(expectedMovieRespons.releaseDate()).isEqualTo(movie.getMovieReleaseDate())
        );
    }
}