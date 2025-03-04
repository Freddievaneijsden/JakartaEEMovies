package example.com;

import example.com.dto.MovieResponse;
import example.com.entity.Movie;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;


//Klass som svarar på frågor från webbapplikationen, dvs vill komma åt resursen
//Heter controller i Spring boot, MVC
@Path("movies")
@Log
public class MovieResource {

    //Alla metoder mot movies i denna klass
    private MovieRepository repository;

    @Inject
    public MovieResource (MovieRepository repository) {
        this.repository = repository;
    }

//    private static final Logger logger = Logger.getLogger(MovieResource.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies (){
        return repository.findAll().toList();
    }

    @GET
    @Path("{id}") //Kopplar id med variabel
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getOneMovie (@PathParam("id") Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Movie not found")
        );

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MovieResponse movie () {
        return new MovieResponse("Gladiator II", 130);
    }

    @GET
    @Path("many")
    @Produces(MediaType.APPLICATION_JSON)
    public Movies manyMovies () {
        List<MovieResponse> movies = new ArrayList<>();
        movies.add(new MovieResponse("Gladiator II", 130));
        movies.add(new MovieResponse("Forest Gump", 120));
        return new Movies(movies, 2);
    }

    public record Movies (List<MovieResponse> Values, int TotalMovies) {}
}
