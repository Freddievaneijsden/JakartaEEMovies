package com.example.presentation;

import com.example.business.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
class MovieResourceTest {

    @Mock
    MovieService movieService;

    @Test
    @DisplayName("Get movie by Id returns movie with same id as paramter")
    void getMovieByIdReturnsMovieWithSameIdAsParamter() {

    }

}