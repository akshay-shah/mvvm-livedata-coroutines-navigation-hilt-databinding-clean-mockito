package com.example.starwars.data.source.remote.service

import com.example.starwars.data.model.FilmResponse
import com.example.starwars.data.model.PlanetResponse
import com.example.starwars.data.model.SearchResponse
import com.example.starwars.data.model.SpecieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api/people/")
    suspend fun searchCharacter(@Query("search") query: String?): SearchResponse

    @GET("/api/films/{id}/")
    suspend fun getFilmDetails(@Path("id") id: String): FilmResponse

    @GET("/api/planets/{id}/")
    suspend fun getPlanetDetails(@Path("id") id: String): PlanetResponse

    @GET("/api/species/{id}/")
    suspend fun getSpecieDetails(@Path("id") id: String): SpecieResponse
}