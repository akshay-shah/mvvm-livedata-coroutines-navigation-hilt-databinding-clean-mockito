package com.example.starwars.data.mapper

import com.example.starwars.mockFilmDetailsResponse
import com.example.starwars.mockFilmResponse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class FilmDetailsResponseMapperTest {
    private lateinit var mapper: FilmDetailsResponseMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mapper = FilmDetailsResponseMapper()
    }

    @Test
    fun `toModel for valid data should return use case response`() {
        val response = mapper.toModel(mockFilmResponse)
        assertEquals(mockFilmDetailsResponse.filmDetailsModel, response.filmDetailsModel)
        assertEquals(mockFilmDetailsResponse.error, response.error)
        assertFalse(response.error)
    }

    @Test
    fun `toModel for invalid data should return use case response error`() {
        val response = mapper.toModel(null)
        assertTrue(response.error)
    }
}