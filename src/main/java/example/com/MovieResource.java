package example.com;

import example.com.dto.CreateMovie;
import example.com.dto.MovieResponse;
import example.com.dto.UpdateMovie;
import example.com.entity.Movie;
import example.com.mapper.MovieMapper;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @Inject
    public MovieResource(MovieRepository repository) {
        this.repository = repository;
    }

    public MovieResource() {
    }

//    private static final Logger logger = Logger.getLogger(MovieResource.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieResponse> getMovies() {
        return repository.findAll()
                .map(MovieResponse::new)
                .toList();
    }

    @GET
    @Path("{id}") //Kopplar id med variabel
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getOneMovie(@PathParam("id") Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Movie not found")
        );
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewMovie(CreateMovie movie) {
        if (movie == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Book cannot be null").build();
        }
        Movie newMovie = MovieMapper.map(movie);
        newMovie = repository.insert(newMovie);

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
        oldMovie.setMoviePrice(movie.price());
        oldMovie.setMovieGenre(movie.genre());
        repository.update(oldMovie);

        return Response.noContent().build();
    }

    @PATCH
    @Path("{id}") //Kopplar id med variabel
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovieFieldByField(UpdateMovie movie, @PathParam("id") Long id) {
        var oldMovie = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Movie not found")
        );
        if (movie.title() != null) oldMovie.setMovieTitle(movie.title());
        if (movie.price() != null) oldMovie.setMoviePrice(movie.price());
        if (movie.genre() != null) oldMovie.setMovieGenre(movie.genre());
        oldMovie.setMovieTitle(movie.title());
        oldMovie.setMoviePrice(movie.price());
        oldMovie.setMovieGenre(movie.genre());
        repository.update(oldMovie);

        return Response.noContent().build();
    }


//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public MovieResponse movie () {
//        return new MovieResponse(1, "Gladiator II", 130, "Action");
//    }
//
//    @GET
//    @Path("many")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Movies manyMovies () {
//        List<MovieResponse> movies = new ArrayList<>();
//        movies.add(new MovieResponse("Gladiator II", 130));
//        movies.add(new MovieResponse("Forest Gump", 120));
//        return new Movies(movies, 2);
//    }
//
//    public record Movies (List<MovieResponse> Values, int TotalMovies) {}
}
