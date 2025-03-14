package com.example.business;

import com.example.dto.MovieResponse;
import com.example.entity.Movie;
import com.example.mapper.MovieMapper;
import com.example.persistence.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository repository;

    @InjectMocks
    private MovieService movieService;

    List<Movie> movies;

    @BeforeEach
    void setUp() {
        // Creating some sample movies
        movies = List.of(
                new Movie() {{
                    setMovieId(1L);
                    setMovieTitle("The Dark Knight");
                    setMovieDescription("Action movie that takes place in Gotham");
                    setMovieReleaseDate(LocalDate.of(2010, 3, 10));
                    setMovieDirector("Christopher Nolan");
                    setMovieDuration(120);
                }},
                new Movie() {{
                    setMovieId(2L);
                    setMovieTitle("Inception");
                    setMovieDescription("A mind-bending thriller about dreams");
                    setMovieReleaseDate(LocalDate.of(2010, 7, 16));
                    setMovieDirector("Christopher Nolan");
                    setMovieDuration(148);
                }}
        );
    }

    @Test
    @DisplayName("GetAllMovies return all Movies in repository")
    void getAllMoviesReturnAllMoviesInRepository() {
        when(repository.findAll()).thenReturn(movies.stream());

        List<MovieResponse> actualResponses = movieService.getAllMovies();

        List<MovieResponse> expectedResponses = movies.stream()
                .map(MovieMapper::map)
                .toList();

        assertThat(actualResponses).containsExactlyElementsOf(expectedResponses);
    }

    @Test
    @DisplayName("GetMovieById should return Movie with given id")
    void getMovieByIdShouldReturnMovieWithGivenId() {
        when(repository.findById(1L)).thenReturn(Optional.of(movies.get(0)));

        MovieResponse actualResponses = movieService.getMovieById(1L);

        MovieResponse expectedResponses = MovieMapper.map(movies.get(0));

        assertThat(actualResponses).isEqualTo(expectedResponses);
    }

}