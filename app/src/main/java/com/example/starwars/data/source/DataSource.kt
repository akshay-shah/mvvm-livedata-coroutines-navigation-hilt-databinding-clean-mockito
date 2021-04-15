package com.example.starwars.data.source

import com.example.starwars.data.model.FilmResponse
import com.example.starwars.data.model.PlanetResponse
import com.example.starwars.data.model.SearchResponse
import com.example.starwars.data.model.SpecieResponse

interface DataSource {
    interface RemoteDataSource {
        suspend fun searchCharacter(query: String?): SearchResponse?
        suspend fun getPlanetDetails(id: String): PlanetResponse?
        suspend fun getSpecieDetails(id: String): SpecieResponse?
        suspend fun getFilmDetails(id: String): FilmResponse?
    }
}