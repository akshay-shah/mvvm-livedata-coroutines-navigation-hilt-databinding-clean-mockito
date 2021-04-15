package com.example.starwars.domain.usecase

import com.example.starwars.domain.model.SearchCharacterDetailsModel
import com.example.starwars.domain.model.SearchCharacterNameModel
import com.example.starwars.domain.repository.SearchCharacterRepository
import javax.inject.Inject

class SearchCharacterUseCase @Inject constructor(private val repository: SearchCharacterRepository) :
    BaseUseCase<SearchCharacterUseCase.SearchCharacterRequest, SearchCharacterUseCase.SearchCharacterResponse>() {

    class SearchCharacterRequest(val query: String? = "") : Request

    class SearchCharacterResponse(
        val characterModel: List<Pair<SearchCharacterNameModel, SearchCharacterDetailsModel>>?,
        val error: Boolean = false
    ) : Response

    override suspend fun executeUseCase(request: SearchCharacterRequest): SearchCharacterResponse =
        repository.searchCharacter(request.query)
}