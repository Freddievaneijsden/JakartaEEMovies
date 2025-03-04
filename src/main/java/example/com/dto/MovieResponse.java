package example.com.dto;

import example.com.entity.Movie;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link example.com.entity.Movie}
 */
public record MovieResponse(Long movieId, @NotBlank(message = "Movie title required") String movieTitle,
                            BigDecimal moviePrice, String movieGenre) implements Serializable {
    public static MovieResponse of (Movie movie) {
        return new MovieResponse(movie.getMovieId(), movie.getMovieTitle(), movie.getMoviePrice(), movie.getMovieGenre());
    }

    public MovieResponse (Movie movie) {
       this(movie.getMovieId(), movie.getMovieTitle(), movie.getMoviePrice(), movie.getMovieGenre());
    }
}