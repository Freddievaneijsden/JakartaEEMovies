package com.example.persistence;

import com.example.entity.Movie;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    //Grundläggande CRUD finns via CrudRepository
    //Kan skriva egna om man följer regler för namngivning

    Optional<Movie> findByMovieTitle(String title);
    List<Movie> findByMovieDirector(String title);
    List<Movie> findByMovieDurationGreaterThan(Integer duration);

}
