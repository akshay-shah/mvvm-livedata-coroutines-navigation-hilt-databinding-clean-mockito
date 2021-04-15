package com.example.starwars.data.source.remote

import com.example.starwars.data.model.FilmResponse
import com.example.starwars.data.model.PlanetResponse
import com.example.starwars.data.model.SearchResponse
import com.example.starwars.data.model.SpecieResponse
import com.example.starwars.data.source.DataSource
import com.example.starwars.data.source.remote.service.ApiService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val remoteService: ApiService) :
    DataSource.RemoteDataSource {

    override suspend fun searchCharacter(query: String?): SearchResponse? =
        try {
            remoteService.searchCharacter(query)
        } catch (error: Exception) {
            null
        }

    override suspend fun getPlanetDetails(id: String): PlanetResponse? =
        try {
            remoteService.getPlanetDetails(id)
        } catch (error: Exception) {
            null
        }

    override suspend fun getSpecieDetails(id: String): SpecieResponse? =
        try {
            remoteService.getSpecieDetails(id)
        } catch (error: Exception) {
            null
        }

    override suspend fun getFilmDetails(id: String): FilmResponse? =
        try {
            remoteService.getFilmDetails(id)
        } catch (error: Exception) {
            null
        }
}