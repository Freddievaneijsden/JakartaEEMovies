package com.example.presentation;

import com.example.business.MovieService;

import org.jboss.resteasy.spi.Dispatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class MovieResourceTest {

    @Mock
    MovieService movieService;

    Dispatcher dispatcher;

    @Test
    @DisplayName("Get movie by Id returns movie with same id as parameter")
    void getMovieByIdReturnsMovieWithSameIdAsParameter() {

    }

}