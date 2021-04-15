package com.example.starwars.presentation.mapper

import com.example.starwars.mockCharacterInfoModel
import com.example.starwars.mockSearchCharacterDetailsModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterInfoMapperTest {

    private lateinit var mapper: CharacterInfoMapper

    @Before
    fun setup() {
        mapper = CharacterInfoMapper()
    }

    @Test
    fun `toModel for non null should return CharacterInfoModel`() {
        val model = mapper.toModel(mockSearchCharacterDetailsModel)
        assertEquals(mockCharacterInfoModel, model)
    }

}