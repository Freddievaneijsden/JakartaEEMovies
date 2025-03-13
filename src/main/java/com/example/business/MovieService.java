package com.example.business;

import com.example.exceptions.BadRequest;
import com.example.exceptions.NotFound;
import com.example.persistence.MovieRepository;
import com.example.dto.CreateMovie;
import com.example.dto.MovieResponse;
import com.example.dto.UpdateMovie;
import com.example.entity.Movie;
import com.example.mapper.MovieMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class MovieService {

    private MovieRepository repository;

    @Inject
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public MovieService() {
    }

    public List<MovieResponse> getAllMovies() {
        return repository.findAll()
                .map(MovieResponse::map)
                .filter(Objects::nonNull)
                .toList();
    }

    public MovieResponse getMovieById(@Positive Long id) {
        if (id == null) throw new BadRequest("Id cannot be null");
        return repository.findById(id)
                .map(MovieResponse::map)
                .orElseThrow(
                () -> new NotFound("Movie with id " + id + " not found")
        );
    }

    public Movie createMovie(@Valid CreateMovie movie) {
        if (movie == null) throw new BadRequest("Movie cannot be null");
        Movie newMovie = MovieMapper.map(movie);
        newMovie = repository.insert(newMovie);
        return newMovie;
    }

    public void updateMovieField(@Valid UpdateMovie movie, @Positive Long id) {
        if (id == null) throw new BadRequest("Id cannot be null");
        var oldMovie = repository.findById(id).orElseThrow(
                () -> new NotFound("Movie with id " + id + " not found")
        );
        if (movie.title() != null) oldMovie.setMovieTitle(movie.title());
        if (movie.duration() != null) oldMovie.setMovieDuration(movie.duration());
        if (movie.director() != null) oldMovie.setMovieDirector(movie.director());
        if (movie.releaseDate() != null) oldMovie.setMovieReleaseDate(movie.releaseDate());
        if (movie.description() != null) oldMovie.setMovieDescription(movie.description());
        oldMovie.setMovieTitle(movie.title());
        oldMovie.setMovieDuration(movie.duration());
        oldMovie.setMovieDirector(movie.director());
        oldMovie.setMovieReleaseDate(movie.releaseDate());
        oldMovie.setMovieDescription(movie.description());
        repository.update(oldMovie);
    }

    public List<MovieResponse> getMoviesByDirector(String movieDirector) {
        if (movieDirector == null) throw new BadRequest("Director cannot be null");
        return repository.findByMovieDirector(movieDirector.trim())
                .stream()
                .map(MovieResponse::map)
                .filter(Objects::nonNull)
                .toList();
    }

    public List<MovieResponse> getMoviesWithDurationGreaterThan(@Positive Integer movieDuration) {
        if (movieDuration == null) throw new BadRequest("Duration cannot be null");
        return repository.findByMovieDurationGreaterThan(movieDuration)
                .stream()
                .map(MovieResponse::map)
                .filter(Objects::nonNull)
                .toList();
    }

    public MovieResponse getMovieByTitle(String movieTitle) {
        if (movieTitle == null) throw new BadRequest("Title cannot be null");
        return repository.findByMovieTitle(movieTitle)
                .map(MovieResponse::map)
                .orElseThrow(
                        () -> new NotFound("Movie with title " + movieTitle + " not found")
                );
    }

    public boolean isValidTitle(String movieTitle) {
        return repository.findByMovieTitle(movieTitle).isEmpty();
    }

}
