package com.example.business;

import com.example.persistence.MovieRepository;
import com.example.dto.CreateMovie;
import com.example.dto.MovieResponse;
import com.example.dto.UpdateMovie;
import com.example.entity.Movie;
import com.example.mapper.MovieMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class MovieService {

    private MovieRepository repository;

    @Inject
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public MovieService(){}

    public List<MovieResponse> getAllMovies() {
        return repository.findAll()
                .map(MovieResponse::new)
                .filter(Objects::nonNull)
                .toList();
    }

   public Movie getMovieById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Movie not found")
        );
    }

    public Movie createMovie(CreateMovie movie) {
        Movie newMovie = MovieMapper.map(movie);
        newMovie = repository.insert(newMovie);
        return newMovie;
    }

    public void updateMovieField(UpdateMovie movie, Long id) {
        var oldMovie = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Movie not found")
        );
        if (movie.title() != null) oldMovie.setMovieTitle(movie.title());
        if (movie.duration() != null) oldMovie.setMovieDuration(movie.duration());
        if (movie.director() != null) oldMovie.setMovieDirector(movie.director());
        oldMovie.setMovieTitle(movie.title());
        oldMovie.setMovieDuration(movie.duration());
        oldMovie.setMovieDirector(movie.director());
        repository.update(oldMovie);
    }
}
