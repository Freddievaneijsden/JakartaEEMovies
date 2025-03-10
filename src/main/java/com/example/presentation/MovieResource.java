package com.example.presentation;

import com.example.persistence.MovieRepository;
import com.example.business.MovieService;
import com.example.dto.CreateMovie;
import com.example.dto.MovieResponse;
import com.example.dto.UpdateMovie;
import com.example.entity.Movie;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
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
    private MovieRepository repository;
    private MovieService movieService;

    @Inject
    public MovieResource(MovieRepository repository, MovieService movieService) {
        this.repository = repository;
        this.movieService = movieService;
    }

    public MovieResource() {
    }

//    private static final Logger logger = Logger.getLogger(MovieResource.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieResponse> getMovies() {
        return movieService.getAllMovies();
    }

    //Viktigt att testa samtliga annotationer
    @GET
    @Path("{id}") //Kopplar id med variabel
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getOneMovie(@PathParam("id") Long id) {
        return movieService.getMovieById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewMovie(@Valid CreateMovie movie) {
        if (movie == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Book cannot be null").build();
        }

        Movie newMovie = movieService.createMovie(movie);

        return Response
                .status(Response.Status.CREATED)
                .header("Location", "/api/movies/" + newMovie.getMovieId())
                .build();
    }

    @PUT
    @Path("{id}") //Kopplar id med variabel
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(UpdateMovie movie, @PathParam("id") Long id) {
        var oldMovie = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Movie not found")
        );
        oldMovie.setMovieTitle(movie.title());
        oldMovie.setMovieDuration(movie.duration());
        oldMovie.setMovieDirector(movie.director());
        oldMovie.setMovieReleaseDate(movie.releaseDate());
        oldMovie.setMovieDescription(movie.description());
        repository.update(oldMovie);

        return Response.noContent().build();
    }

    @PATCH
    @Path("{id}") //Kopplar id med variabel
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovieFieldByField(UpdateMovie movie, @PathParam("id") Long id) {
        movieService.updateMovieField(movie, id);
        return Response.noContent().build();
    }
}
