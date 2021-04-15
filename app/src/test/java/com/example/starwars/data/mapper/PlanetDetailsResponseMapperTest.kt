package com.example.starwars.data.mapper

import com.example.starwars.mockPlanetDetailsResponse
import com.example.starwars.mockPlanetResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class PlanetDetailsResponseMapperTest {

    private lateinit var mapper: PlanetDetailsResponseMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mapper = PlanetDetailsResponseMapper()
    }

    @Test
    fun `toModel for valid data should return use case response`() {
        val response = mapper.toModel(mockPlanetResponse)
        Assert.assertEquals(
            mockPlanetDetailsResponse.planetDetailsModel,
            response.planetDetailsModel
        )
        Assert.assertEquals(mockPlanetDetailsResponse.error, response.error)
        Assert.assertFalse(response.error)
    }

    @Test
    fun `toModel for invalid data should return use case response error`() {
        val response = mapper.toModel(null)
        Assert.assertTrue(response.error)
    }
}