package example.com;

import example.com.dto.MovieResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


//Klass som svarar på frågor från webbapplikationen, dvs vill komma åt resursen
//Heter controller i Spring boot, MVC
@Path("movies")
public class MovieResource {

    private static final Logger logger = Logger.getLogger(MovieResource.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

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
