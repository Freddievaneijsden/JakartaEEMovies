package com.example.presentation;

import com.example.exceptions.BadRequest;
import com.example.business.MovieService;
import com.example.dto.CreateMovie;
import com.example.dto.MovieResponse;
import com.example.dto.UpdateMovie;
import com.example.entity.Movie;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;

import java.util.List;


//Klass som svarar på frågor från webbapplikationen, dvs vill komma åt resursen

//Heter controller i Spring boot, MVC
@Path("movies")
@Log
public class MovieResource {

    //Alla metoder mot movies i denna klass
    private MovieService movieService;

    @Inject
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    public MovieResource() {
    }

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieResponse> getMovies(@QueryParam("director") String director,
                                         @QueryParam("duration") @Positive Integer duration) {
        if (director != null && !director.isEmpty()) {
            //api/movies?director=
            return movieService.getMoviesByDirector(director);
        }
        else if (duration != null) {
            return movieService.getMoviesWithDurationGreaterThan(duration);
        }
        else {
            return movieService.getAllMovies();
        }
    }

    //Viktigt att testa samtliga annotationer
    @GET
    @Path("{id}") //Kopplar id med variabel
    @Produces(MediaType.APPLICATION_JSON)
    public MovieResponse getOneMovie(@PathParam("id") @Positive Long id) {
        if (id == null) throw new BadRequest("Id cannot be null");
        return movieService.getMovieById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewMovie(@Valid CreateMovie movie) {
        if (movie == null) {
            throw new BadRequest("Movie cannot be null");
        }

        Movie newMovie = movieService.createMovie(movie);
        log.info("Creating new movie: " + newMovie);

        return Response
                .status(Response.Status.CREATED)
                .header("Location", "/api/movies/" + newMovie.getMovieId())
                .build();
    }

//    @PUT
//    @Path("{id}") //Kopplar id med variabel
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateMovie(@Valid UpdateMovie movie, @PathParam("id") Long id) {
//        var oldMovie = repository.findById(id).orElseThrow(
//                () -> new NotFoundException("Movie not found")
//        );
//        oldMovie.setMovieTitle(movie.title());
//        oldMovie.setMovieDuration(movie.duration());
//        oldMovie.setMovieDirector(movie.director());
//        oldMovie.setMovieReleaseDate(movie.releaseDate());
//        oldMovie.setMovieDescription(movie.description());
//        repository.update(oldMovie);
//        log.info("Updating movie: " + movie);
//
//
//        return Response.noContent().build();
//    }

    @PATCH
    @Path("{id}") //Kopplar id med variabel
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovieFieldByField(@Valid UpdateMovie movie, @PathParam("id") @Positive @NotBlank Long id) {
        movieService.updateMovieField(movie, id);
        log.info("Updating movie: " + movie);
        return Response.noContent().build();
    }

    ///movies/by-title?title={title}
    @GET
    @Path("by-title")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieResponse getMovieByTitle(@QueryParam("title") @NotBlank String title) {
        return movieService.getMovieByTitle(title);
    }
}
