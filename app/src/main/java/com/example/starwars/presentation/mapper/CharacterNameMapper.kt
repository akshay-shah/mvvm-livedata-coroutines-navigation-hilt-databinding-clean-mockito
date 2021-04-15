package com.example.starwars.presentation.mapper

import com.example.starwars.domain.model.SearchCharacterNameModel
import com.example.starwars.presentation.model.CharacterNamesModel

class CharacterNameMapper : ModelMapper<List<SearchCharacterNameModel>, CharacterNamesModel> {

    override fun toModel(request: List<SearchCharacterNameModel>?): CharacterNamesModel {
        return CharacterNamesModel(characterNamesList = request?.map { it.name })
    }
}