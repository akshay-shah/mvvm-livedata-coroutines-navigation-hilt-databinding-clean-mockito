package com.example.starwars.domain.usecase

import com.example.starwars.domain.model.FilmDetailsModel
import com.example.starwars.domain.repository.FilmRepository
import javax.inject.Inject

class GetFilmDetailsUseCase @Inject constructor(private val repository: FilmRepository) :
    BaseUseCase<GetFilmDetailsUseCase.GetFilmDetailsRequest, GetFilmDetailsUseCase.GetFilmDetailsResponse>() {

    class GetFilmDetailsRequest(val id: String) : Request
    class GetFilmDetailsResponse(
        val filmDetailsModel: FilmDetailsModel?,
        val error: Boolean = false
    ) : Response

    override suspend fun executeUseCase(request: GetFilmDetailsRequest): GetFilmDetailsResponse =
        repository.getFilmDetails(request.id)
}