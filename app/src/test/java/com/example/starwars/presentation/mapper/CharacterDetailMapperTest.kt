package com.example.starwars.presentation.mapper

import com.example.starwars.mockFilmDetailsResponse
import com.example.starwars.mockModelCharacterDetails
import com.example.starwars.mockPlanetDetailsResponse
import com.example.starwars.mockSpecieDetailsResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterDetailMapperTest {
    private lateinit var mapper: CharacterDetailMapper

    @Before
    fun setUp() {
        mapper = CharacterDetailMapper()
    }

    @Test
    fun `toModel for non null input should return CharacterDetailsModel`() {
        val model = mapper.toModel(
            "birth_year", "height", "name", listOf(mockSpecieDetailsResponse),
            listOf(mockFilmDetailsResponse), mockPlanetDetailsResponse
        )
        assertEquals(mockModelCharacterDetails, model)
    }
}