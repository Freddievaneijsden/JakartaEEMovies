package com.example.persistence;

import com.example.entity.Movie;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    //Grundläggande CRUD finns via CrudRepository
    //Kan skriva egna om man följer regler för namngivning

//    @Find
//    Optional<Movie> findByTitle(String title);
    @Find
    List<Movie> findByMovieDirector(String movieDirector);

    @Query("select m from Movie m where movieDuration > :movieDuration")
    List<Movie> findByMovieDurationGreaterThan(Integer movieDuration);

}
