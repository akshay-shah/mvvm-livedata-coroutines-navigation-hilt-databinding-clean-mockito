package com.example.starwars.data.mapper

import com.example.starwars.mockSpecieDetailsResponse
import com.example.starwars.mockSpecieResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class SpecieDetailsResponseMapperTest {
    private lateinit var mapper: SpecieDetailsResponseMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mapper = SpecieDetailsResponseMapper()
    }

    @Test
    fun `toModel for valid data should return use case response`() {
        val response = mapper.toModel(mockSpecieResponse)
        Assert.assertEquals(
            mockSpecieDetailsResponse.specieDetailsModel,
            response.specieDetailsModel
        )
        Assert.assertEquals(mockSpecieDetailsResponse.error, response.error)
        Assert.assertFalse(response.error)
    }

    @Test
    fun `toModel for invalid data should return use case response error`() {
        val response = mapper.toModel(null)
        Assert.assertTrue(response.error)
    }
}