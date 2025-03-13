package com.example.mapper;

import com.example.dto.CreateMovie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MovieMapperTest {

    @Test
    @DisplayName("MapWithNoArgumentReturnNull")
    void mapWithNoArgumentReturnNull() {
        CreateMovie newMovie = null;

        assertThat(MovieMapper.map(newMovie)).isEqualTo(null);
    }
}