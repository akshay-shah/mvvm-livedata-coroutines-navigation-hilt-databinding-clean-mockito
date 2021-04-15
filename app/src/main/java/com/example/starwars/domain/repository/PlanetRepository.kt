package com.example.starwars.domain.repository

import com.example.starwars.domain.usecase.GetPlanetDetailsUseCase

interface PlanetRepository {
    suspend fun getPlanetDetails(id: String): GetPlanetDetailsUseCase.GetPlanetDetailsResponse
}