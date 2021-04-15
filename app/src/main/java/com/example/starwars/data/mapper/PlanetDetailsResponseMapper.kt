package com.example.starwars.data.mapper

import com.example.starwars.data.model.PlanetResponse
import com.example.starwars.domain.model.PlanetDetailsModel
import com.example.starwars.domain.usecase.GetPlanetDetailsUseCase

class PlanetDetailsResponseMapper :
    ResponseMapper<PlanetResponse, GetPlanetDetailsUseCase.GetPlanetDetailsResponse> {
    override fun toModel(response: PlanetResponse?): GetPlanetDetailsUseCase.GetPlanetDetailsResponse {
        return when (response) {
            null -> GetPlanetDetailsUseCase.GetPlanetDetailsResponse(
                planetDetailsModel = null,
                error = true
            )
            else -> GetPlanetDetailsUseCase.GetPlanetDetailsResponse(
                planetDetailsModel = response.let {
                    PlanetDetailsModel(
                        name = it.name,
                        population = it.population
                    )
                }
            )
        }
    }
}