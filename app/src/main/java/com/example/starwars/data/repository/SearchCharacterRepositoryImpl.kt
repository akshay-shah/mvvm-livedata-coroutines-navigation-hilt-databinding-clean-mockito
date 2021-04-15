package com.example.starwars.data.repository

import com.example.starwars.data.mapper.SearchCharacterResponseMapper
import com.example.starwars.data.source.DataSource
import com.example.starwars.domain.repository.SearchCharacterRepository
import com.example.starwars.domain.usecase.SearchCharacterUseCase
import javax.inject.Inject

class SearchCharacterRepositoryImpl @Inject constructor(
    private val responseMapper: SearchCharacterResponseMapper,
    private val remoteDataSource: DataSource.RemoteDataSource
) : SearchCharacterRepository {

    override suspend fun searchCharacter(queryString: String?): SearchCharacterUseCase.SearchCharacterResponse =
        responseMapper.toModel(remoteDataSource.searchCharacter(queryString))

}