package com.example.starwars.data.repository

import com.example.starwars.data.mapper.FilmDetailsResponseMapper
import com.example.starwars.data.source.DataSource
import com.example.starwars.domain.repository.FilmRepository
import com.example.starwars.domain.usecase.GetFilmDetailsUseCase
import javax.inject.Inject

class FilmDetailsRepositoryImpl @Inject constructor(
    private val responseMapper: FilmDetailsResponseMapper,
    private val remoteDataSource: DataSource.RemoteDataSource
) : FilmRepository {
    override suspend fun getFilmDetails(id: String): GetFilmDetailsUseCase.GetFilmDetailsResponse =
        responseMapper.toModel(remoteDataSource.getFilmDetails(id))
}