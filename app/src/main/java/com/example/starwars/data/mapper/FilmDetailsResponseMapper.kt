package com.example.starwars.data.mapper

import com.example.starwars.data.model.FilmResponse
import com.example.starwars.domain.model.FilmDetailsModel
import com.example.starwars.domain.usecase.GetFilmDetailsUseCase

class FilmDetailsResponseMapper :
    ResponseMapper<FilmResponse, GetFilmDetailsUseCase.GetFilmDetailsResponse> {
    override fun toModel(response: FilmResponse?): GetFilmDetailsUseCase.GetFilmDetailsResponse {
        return when (response) {
            null -> GetFilmDetailsUseCase.GetFilmDetailsResponse(
                error = true,
                filmDetailsModel = null
            )
            else -> GetFilmDetailsUseCase.GetFilmDetailsResponse(filmDetailsModel = response.let {
                FilmDetailsModel(
                    name = it.title,
                    descripton = it.opening_crawl
                )
            })
        }
    }
}