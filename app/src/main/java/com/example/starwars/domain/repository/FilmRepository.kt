package com.example.starwars.domain.repository

import com.example.starwars.domain.usecase.GetFilmDetailsUseCase

interface FilmRepository {
    suspend fun getFilmDetails(id: String): GetFilmDetailsUseCase.GetFilmDetailsResponse
}