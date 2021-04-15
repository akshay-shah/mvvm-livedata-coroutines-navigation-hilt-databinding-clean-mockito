package com.example.starwars.data.repository

import com.example.starwars.data.mapper.PlanetDetailsResponseMapper
import com.example.starwars.data.source.DataSource
import com.example.starwars.domain.repository.PlanetRepository
import com.example.starwars.domain.usecase.GetPlanetDetailsUseCase
import javax.inject.Inject

class PlanetDetailsRepositoryImpl @Inject constructor(
    private val responseMapper: PlanetDetailsResponseMapper,
    private val remoteDataSource: DataSource.RemoteDataSource
) : PlanetRepository {
    override suspend fun getPlanetDetails(id: String): GetPlanetDetailsUseCase.GetPlanetDetailsResponse =
        responseMapper.toModel(remoteDataSource.getPlanetDetails(id))
}