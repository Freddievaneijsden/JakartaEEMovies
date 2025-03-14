package com.example.business;

import com.example.dto.CreateMovie;
import com.example.dto.MovieResponse;
import com.example.dto.UpdateMovie;
import com.example.entity.Movie;
import com.example.mapper.MovieMapper;
import com.example.persistence.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
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
                }},
                new Movie() {{
                    setMovieId(3L);
                    setMovieTitle("Gladiator");
                    setMovieDescription("Action movie about roman general");
                    setMovieReleaseDate(LocalDate.of(2000, 9, 16));
                    setMovieDirector("Ridley scott");
                    setMovieDuration(125);
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

    @Test
    @DisplayName("CreateMovie should insert new movie in repository")
    void createMovieShouldInsertNewMovieInRepository() {
        CreateMovie createMovie = new CreateMovie(
                "Avengers",
                "Anthony Russo",
                120,
                LocalDate.of(2012, 3, 10),
                "Action movie with marvel heroes");

        Movie expectedMovie = new Movie();
        expectedMovie.setMovieTitle("Avengers");
        expectedMovie.setMovieDirector("Anthony Russo");
        expectedMovie.setMovieDuration(120);
        expectedMovie.setMovieReleaseDate(LocalDate.of(2012, 3, 10));
        expectedMovie.setMovieDescription("Action movie with marvel heroes");

        when(repository.insert(Mockito.any(Movie.class))).thenReturn(expectedMovie);
        Movie actualMovie = movieService.createMovie(createMovie);

        assertThat(actualMovie).usingRecursiveComparison().isEqualTo(expectedMovie);
    }

    @Test
    @DisplayName("GetMovieByDirector should return movie with given director")
    void getMovieByDirectorShouldReturnMovieWithGivenDirector() {
        List<Movie> nolanMovies = movies.subList(0, 2);
        when(repository.findByMovieDirector("Christopher Nolan")).thenReturn(nolanMovies);

        List<MovieResponse> actualResponses = movieService.getMoviesByDirector("Christopher Nolan");

        List<MovieResponse> expectedResponses = nolanMovies.stream()
                .map(MovieMapper::map)
                .toList();

        assertThat(actualResponses).containsExactlyElementsOf(expectedResponses);
    }

    @Test
    @DisplayName("updateMovieField should change field of Movie")
    void updateMovieFieldShouldChangeFieldOfMovie() {
        UpdateMovie updateMovie = new UpdateMovie(
                "Updated Title",
                null,
                null,
                null,
                null
        );

        when(repository.findById(1L)).thenReturn(Optional.of(movies.get(0)));

        movieService.updateMovieField(updateMovie, 1L);

        ArgumentCaptor<Movie> movieCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(repository).update(movieCaptor.capture());

        Movie updatedMovie = movieCaptor.getValue();

        assertThat(updatedMovie.getMovieTitle()).isEqualTo("Updated Title");
    }

    @Test
    @DisplayName("getMoviesWithDurationGreaterThan should return movies with duration greater then given integer")
    void getMoviesWithDurationGreaterThanShouldReturnMoviesWithDurationGreaterThenGivenInteger() {
        List<Movie> moviesWithDurationGreaterThan120 = movies.subList(1, 2);

        when(repository.findByMovieDurationGreaterThan(120)).thenReturn(moviesWithDurationGreaterThan120);

        List<MovieResponse> actualResponses = movieService.getMoviesWithDurationGreaterThan(120);

        List<MovieResponse> expectedResponses = moviesWithDurationGreaterThan120.stream()
                .map(MovieMapper::map)
                .toList();

        assertThat(actualResponses).containsExactlyElementsOf(expectedResponses);
    }

    @Test
    @DisplayName("getMovieByTitle should return Movie with given title")
    void getMovieByTitleShouldReturnMovieWithGivenTitle() {
        when(repository.findByMovieTitle("The Dark Knight")).thenReturn(Optional.of(movies.get(0)));

        MovieResponse actualResponses = movieService.getMovieByTitle("The Dark Knight");

        MovieResponse expectedResponses = MovieMapper.map(movies.get(0));

        assertThat(actualResponses).isEqualTo(expectedResponses);
    }


}