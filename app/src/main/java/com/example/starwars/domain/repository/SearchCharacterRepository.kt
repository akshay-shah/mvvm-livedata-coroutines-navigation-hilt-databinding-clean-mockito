package com.example.starwars.domain.repository

import com.example.starwars.domain.usecase.SearchCharacterUseCase

interface SearchCharacterRepository {
    suspend fun searchCharacter(queryString: String?): SearchCharacterUseCase.SearchCharacterResponse
}