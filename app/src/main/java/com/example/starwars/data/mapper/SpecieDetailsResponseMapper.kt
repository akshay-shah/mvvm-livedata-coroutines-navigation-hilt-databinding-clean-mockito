package com.example.starwars.data.mapper

import com.example.starwars.data.model.SpecieResponse
import com.example.starwars.domain.model.SpecieDetailsModel
import com.example.starwars.domain.usecase.GetSpecieDetailsUseCase

class SpecieDetailsResponseMapper :
    ResponseMapper<SpecieResponse, GetSpecieDetailsUseCase.GetSpecieDetailsResponse> {
    override fun toModel(response: SpecieResponse?): GetSpecieDetailsUseCase.GetSpecieDetailsResponse {
        return when (response) {
            null -> GetSpecieDetailsUseCase.GetSpecieDetailsResponse(
                specieDetailsModel = null,
                error = true
            )
            else -> GetSpecieDetailsUseCase.GetSpecieDetailsResponse(specieDetailsModel = response.let {
                SpecieDetailsModel(
                    name = it.name,
                    language = it.language
                )
            })
        }
    }
}