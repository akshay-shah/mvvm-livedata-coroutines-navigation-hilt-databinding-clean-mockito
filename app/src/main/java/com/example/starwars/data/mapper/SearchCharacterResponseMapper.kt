package com.example.starwars.data.mapper

import com.example.starwars.data.model.SearchResponse
import com.example.starwars.domain.model.SearchCharacterDetailsModel
import com.example.starwars.domain.model.SearchCharacterNameModel
import com.example.starwars.domain.usecase.SearchCharacterUseCase

class SearchCharacterResponseMapper :
    ResponseMapper<SearchResponse?, SearchCharacterUseCase.SearchCharacterResponse> {

    override fun toModel(response: SearchResponse?): SearchCharacterUseCase.SearchCharacterResponse =
        when (response) {
            null -> SearchCharacterUseCase.SearchCharacterResponse(
                characterModel = null,
                error = true
            )
            else -> SearchCharacterUseCase.SearchCharacterResponse(characterModel = response.results.map {
                with(it) {
                    SearchCharacterNameModel(
                        name
                    ) to SearchCharacterDetailsModel(
                        birth_year,
                        films,
                        height,
                        homeworld,
                        name,
                        species
                    )
                }
            })
        }
}