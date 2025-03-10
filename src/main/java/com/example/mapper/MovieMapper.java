package com.example.mapper;

import com.example.dto.CreateMovie;
import com.example.dto.MovieResponse;
import com.example.entity.Movie;

public class MovieMapper {

    public static MovieResponse map (Movie movie) {
        if( null == movie)
            return null;
        return new MovieResponse(movie.getMovieId(), movie.getMovieTitle(), movie.getMovieDuration(), movie.getMovieDirector());
    }

    public static Movie map(CreateMovie movie) {
        if (movie == null) return null;
        Movie newMovie = new Movie();
        newMovie.setMovieTitle(movie.title());
        newMovie.setMovieDuration(movie.duration());
        newMovie.setMovieDirector(movie.director());
        return newMovie;
    }

    //Man kan även göra en egen klass som mappar mellan objekt istället för att ha metoden i DTO
}
