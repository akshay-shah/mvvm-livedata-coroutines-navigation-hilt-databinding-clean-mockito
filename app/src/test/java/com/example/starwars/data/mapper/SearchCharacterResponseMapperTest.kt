package com.example.starwars.data.mapper

import com.example.starwars.mockSearchCharacterResponse
import com.example.starwars.mockSearchResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class SearchCharacterResponseMapperTest {
    private lateinit var mapper: SearchCharacterResponseMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mapper = SearchCharacterResponseMapper()
    }

    @Test
    fun `toModel for valid data should return use case response`() {
        val response = mapper.toModel(mockSearchResponse)
        Assert.assertEquals(
            mockSearchCharacterResponse.characterModel,
            response.characterModel
        )
        Assert.assertEquals(mockSearchCharacterResponse.error, response.error)
        Assert.assertFalse(response.error)
    }

    @Test
    fun `toModel for invalid data should return use case response error`() {
        val response = mapper.toModel(null)
        Assert.assertTrue(response.error)
    }
}