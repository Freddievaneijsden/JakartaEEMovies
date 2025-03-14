package com.example.business;

import com.example.dto.CreateMovie;
import com.example.dto.MovieResponse;
import com.example.dto.UpdateMovie;
import com.example.entity.Movie;
import com.example.exceptions.BadRequest;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository repository;

    @InjectMocks
    private MovieService movieService;

    List<Movie> movies;
    List<MovieResponse> movieResponses;

    @BeforeEach
    void setUp() {
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
                    setMovieDescription("Action movie about a roman general");
                    setMovieReleaseDate(LocalDate.of(2000, 9, 16));
                    setMovieDirector("Ridley Scott");
                    setMovieDuration(125);
                }}
        );

        movieResponses = List.of(
                new MovieResponse(1L, "The Dark Knight", 120, "Christopher Nolan", LocalDate.of(2010, 3, 10), "Action movie that takes place in Gotham"),
                new MovieResponse(2L, "Inception", 148, "Christopher Nolan", LocalDate.of(2010, 7, 16), "A mind-bending thriller about dreams"),
                new MovieResponse(3L, "Gladiator", 125, "Ridley Scott", LocalDate.of(2000, 9, 16), "Action movie about a roman general")
        );
    }

    @Test
    @DisplayName("GetAllMovies return all Movies in repository")
    void getAllMoviesReturnAllMoviesInRepository() {
        when(repository.findAll()).thenReturn(movies.stream());

        List<MovieResponse> actualResponses = movieService.getAllMovies();

        List<MovieResponse> expectedResponses = movieResponses;

        assertThat(actualResponses).containsExactlyElementsOf(expectedResponses);
    }

    @Test
    @DisplayName("GetMovieById should return Movie with given id")
    void getMovieByIdShouldReturnMovieWithGivenId() {
        when(repository.findById(1L)).thenReturn(Optional.of(movies.get(0)));

        MovieResponse actualResponses = movieService.getMovieById(1L);

        MovieResponse expectedResponses = movieResponses.get(0);

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
        List<MovieResponse> nolanMovieResponses = movieResponses.subList(0, 2);

        when(repository.findByMovieDirector("Christopher Nolan")).thenReturn(nolanMovies);

        List<MovieResponse> actualResponses = movieService.getMoviesByDirector("Christopher Nolan");

        assertThat(actualResponses).containsExactlyElementsOf(nolanMovieResponses);
    }

    @Test
    @DisplayName("UpdateMovieField should change field of Movie")
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
    @DisplayName("GetMoviesWithDurationGreaterThan should return movies with duration greater then given integer")
    void getMoviesWithDurationGreaterThanShouldReturnMoviesWithDurationGreaterThenGivenInteger() {
        List<Movie> moviesWithDurationGreaterThan120 = movies.subList(1, 2);
        List<MovieResponse> moviesResponsesWithDurationGreaterThan120 = movieResponses.subList(1, 2);

        when(repository.findByMovieDurationGreaterThan(120)).thenReturn(moviesWithDurationGreaterThan120);

        List<MovieResponse> actualResponses = movieService.getMoviesWithDurationGreaterThan(120);

        assertThat(actualResponses).containsExactlyElementsOf(moviesResponsesWithDurationGreaterThan120);
    }

    @Test
    @DisplayName("GetMovieByTitle should return Movie with given title")
    void getMovieByTitleShouldReturnMovieWithGivenTitle() {
        when(repository.findByMovieTitle("The Dark Knight")).thenReturn(Optional.of(movies.get(0)));

        MovieResponse actualResponse = movieService.getMovieByTitle("The Dark Knight");

        MovieResponse expectedResponse = movieResponses.get(0);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("GetMovieByTitle should throw exception when title is null")
    void getMovieByTitleShouldThrowExceptionWhenTitleIsNull() {
        assertThrows(BadRequest.class, () -> movieService.getMovieByTitle(null));
    }

    @Test
    @DisplayName("GetMoviesWithDurationGreaterThan should throw exception when duration is null")
    void getMoviesWithDurationGreaterThanShouldThrowExceptionWhenDurationIsNull() {
        assertThrows(BadRequest.class, () -> movieService.getMoviesWithDurationGreaterThan(null));
    }

    @Test
    @DisplayName("GetMoviesByDirector should throw exception when director is null")
    void getMoviesByDirectorShouldThrowExceptionWhenDirectorIsNull() {
        assertThrows(BadRequest.class, () -> movieService.getMoviesByDirector(null));
    }

    @Test
    @DisplayName("GetMovieById should throw exception when id is null")
    void getMovieByIdShouldThrowExceptionWhenIdIsNull() {
        assertThrows(BadRequest.class, () -> movieService.getMovieById(null));
    }

}