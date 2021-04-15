package com.example.starwars.presentation.mapper

import com.example.starwars.mockCharacterNamesModel
import com.example.starwars.mockSearchCharacterNameModelList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterNameMapperTest {
    private lateinit var mapper: CharacterNameMapper

    @Before
    fun setup() {
        mapper = CharacterNameMapper()
    }

    @Test
    fun `toModel for non null should return CharacterNamesModel`() {
        val model = mapper.toModel(mockSearchCharacterNameModelList)
        assertEquals(mockCharacterNamesModel, model)

    }

    @Test
    fun `toModel for null should return null character name list`() {
        val model = mapper.toModel(null)
        assertEquals(mockCharacterNamesModel.copy(null), model)

    }
}