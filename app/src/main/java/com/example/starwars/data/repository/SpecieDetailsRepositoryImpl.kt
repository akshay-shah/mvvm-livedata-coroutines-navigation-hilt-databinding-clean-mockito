package com.example.starwars.data.repository

import com.example.starwars.data.mapper.SpecieDetailsResponseMapper
import com.example.starwars.data.source.DataSource
import com.example.starwars.domain.repository.SpecieRepository
import com.example.starwars.domain.usecase.GetSpecieDetailsUseCase
import javax.inject.Inject

class SpecieDetailsRepositoryImpl @Inject constructor(
    private val responseMapper: SpecieDetailsResponseMapper,
    private val remoteDataSource: DataSource.RemoteDataSource
) : SpecieRepository {
    override suspend fun getSpecieDetails(id: String): GetSpecieDetailsUseCase.GetSpecieDetailsResponse =
        responseMapper.toModel(remoteDataSource.getSpecieDetails(id))
}