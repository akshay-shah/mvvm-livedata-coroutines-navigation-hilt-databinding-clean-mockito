package com.example.starwars.domain.repository

import com.example.starwars.domain.usecase.GetSpecieDetailsUseCase

interface SpecieRepository {
    suspend fun getSpecieDetails(id: String): GetSpecieDetailsUseCase.GetSpecieDetailsResponse
}